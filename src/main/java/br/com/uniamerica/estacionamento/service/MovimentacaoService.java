package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.config.ValidaCPF;
import br.com.uniamerica.estacionamento.config.ValidaPlaca;
import br.com.uniamerica.estacionamento.config.ValidaTelefone;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ValidaCPF validaCPF;
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private CondutorRepository condutorRepository;

    @Transactional
    public void cadastraMovimentacao(Movimentacao movimentacao){
        if(movimentacao.getId()!=null){
            throw new RuntimeException("O id deve ser gerado pelo banco");
        }
        if(movimentacao.getVeiculo()==null){
            throw new RuntimeException("O id de veiculo está incorreto");
        }
        if(movimentacao.getCondutor()==null) {
            throw new RuntimeException("O id de condutor está incoreto");
        }
        if("".equals(movimentacao.getEntrada())){
            throw new RuntimeException("A movimentação não possui uma entrada (deve conter!)");
        }
        if(movimentacao.getSaida()!=null) {
            movimentacao.setValorMulta(BigDecimal.ZERO);
            movimentacao.setValorDesconto(BigDecimal.ZERO);
            if (movimentacao.getSaida() != null) {
                LocalTime tempo = movimentacao.getSaida()
                        .minusHours(movimentacao.getEntrada().getHour())
                        .minusMinutes(movimentacao.getEntrada().getMinute())
                        .minusSeconds(movimentacao.getEntrada().getSecond());
                movimentacao.setTempo(tempo);
            }
            if (configuracaoRepository.findValorMinutoMulta() != null) {
                movimentacao.setValorMinutoMulta(configuracaoRepository.findValorMinutoMulta());
            }
            if (configuracaoRepository.findValorHora() != null) {
                movimentacao.setValorHora(configuracaoRepository.findValorHora());
            }
            if (condutorRepository.findTempoDesconto() != null) {
                movimentacao.setTempoDesconto(condutorRepository.findTempoDesconto());
            }
            if (movimentacao.getEntrada().isBefore(configuracaoRepository.findInicioExpediente())) {
                Duration tempoMulta = Duration.between(movimentacao.getEntrada(), configuracaoRepository.findInicioExpediente());
                movimentacao.setTempoMulta(tempoMulta.toMinutes());
            }
            if (movimentacao.getSaida().isAfter(configuracaoRepository.findFimExpediente())) {
                Duration tempoMulta = Duration.between(configuracaoRepository.findFimExpediente(), movimentacao.getSaida());
                if (movimentacao.getTempoMulta() != null) {
                    movimentacao.setTempoMulta(movimentacao.getTempoMulta() + tempoMulta.toMinutes());
                } else {
                    movimentacao.setTempoMulta(tempoMulta.toMinutes());
                }
            }
            if (movimentacao.getTempoMulta() != null) {
                movimentacao.setValorMulta(movimentacao.getValorMinutoMulta().multiply(BigDecimal.valueOf(movimentacao.getTempoMulta())));
            }
                if (configuracaoRepository.findGerarDesconto()) {
                    movimentacao.setValorDesconto(movimentacao.getValorHora().multiply(BigDecimal.valueOf(movimentacao.getTempoDesconto().getHour())));
                    condutorRepository.findById(movimentacao.getCondutor().getId()).get().setTempoDesconto(LocalTime.of(0, 0));
                }
            if(movimentacao.getTempo()!=null){
                BigDecimal valorTotal;
                if(movimentacao.getTempo().getMinute()!=0){
                    LocalTime tempoNovo = movimentacao.getTempo().plusHours(1);
                    valorTotal = movimentacao.getValorHora().multiply(new BigDecimal(tempoNovo.getHour()));
                }else{
                    valorTotal = movimentacao.getValorHora().multiply(new BigDecimal(movimentacao.getTempo().getHour()));
                }
                movimentacao.setValorTotal(movimentacao.getValorMulta().add(valorTotal.subtract(movimentacao.getValorDesconto())));
            }
            if (movimentacao.getTempoDesconto()!=null){
                condutorRepository.findById(movimentacao.getCondutor().getId()).get().setTempoPago(condutorRepository.findById(movimentacao.getCondutor().getId()).get().getTempoPago().plusHours(movimentacao.getTempo().getHour()).plusMinutes(movimentacao.getTempo().getMinute()));
            }
        }

        this.movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void atualizaMovimentacao(final Long id, Movimentacao movimentacao){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);
        if(movimentacaoBanco==null || !movimentacaoBanco.getId().equals(movimentacao.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if(movimentacao.getVeiculo()==null){
            throw new RuntimeException("O id de veiculo está incorreto");
        }
        if(movimentacao.getCondutor()==null){
            throw new RuntimeException("O id de condutor está incoreto");
        }
        if(movimentacao.getCadastro()==null || "".equals(movimentacao.getCadastro())){
            movimentacao.setCadastro(movimentacaoRepository.findById(movimentacao.getId()).get().getCadastro());
        }
        if("".equals(movimentacao.getEntrada())){
            throw new RuntimeException("A movimentação não possui uma entrada (deve conter!)");
        }
        movimentacao.setValorMulta(BigDecimal.ZERO);
        movimentacao.setValorDesconto(BigDecimal.ZERO);
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond());
            movimentacao.setTempo(tempo);
        }
        if(configuracaoRepository.findValorMinutoMulta()!=null){
            movimentacao.setValorMinutoMulta(configuracaoRepository.findValorMinutoMulta());
        }
        if(configuracaoRepository.findValorHora()!=null){
            movimentacao.setValorHora(configuracaoRepository.findValorHora());
        }
        if(condutorRepository.findTempoDesconto()!=null){
            movimentacao.setTempoDesconto(condutorRepository.findTempoDesconto());
        }
        if (movimentacao.getEntrada().isBefore(configuracaoRepository.findInicioExpediente())) {
            Duration tempoMulta = Duration.between(movimentacao.getEntrada(), configuracaoRepository.findInicioExpediente());
            movimentacao.setTempoMulta(tempoMulta.toMinutes());
        }
        if (movimentacao.getSaida().isAfter(configuracaoRepository.findFimExpediente())) {
            Duration tempoMulta = Duration.between(configuracaoRepository.findFimExpediente(), movimentacao.getSaida());
            if (movimentacao.getTempoMulta() != null) {
                movimentacao.setTempoMulta(movimentacao.getTempoMulta() + tempoMulta.toMinutes());
            } else {
                movimentacao.setTempoMulta(tempoMulta.toMinutes());
            }
        }
        if(movimentacao.getTempoMulta()!=null){
            movimentacao.setValorMulta(movimentacao.getValorMinutoMulta().multiply(BigDecimal.valueOf(movimentacao.getTempoMulta())));
        }
        if(configuracaoRepository.findGerarDesconto()){
            movimentacao.setValorDesconto(movimentacao.getValorHora().multiply(BigDecimal.valueOf(movimentacao.getTempoDesconto().getHour())));
            condutorRepository.findById(movimentacao.getCondutor().getId()).get().setTempoDesconto(LocalTime.of(0, 0));
        }
        if(movimentacao.getTempo()!=null){
            BigDecimal valorTotal;
            if(movimentacao.getTempo().getMinute()!=0){
                LocalTime tempoNovo = movimentacao.getTempo().plusHours(1);
                valorTotal = movimentacao.getValorHora().multiply(new BigDecimal(tempoNovo.getHour()));
            }else{
                valorTotal = movimentacao.getValorHora().multiply(new BigDecimal(movimentacao.getTempo().getHour()));
            }
            movimentacao.setValorTotal(movimentacao.getValorMulta().add(valorTotal.subtract(movimentacao.getValorDesconto())));
        }
        if (movimentacao.getTempoDesconto()!=null){
            condutorRepository.findById(movimentacao.getCondutor().getId()).get().setTempoPago(condutorRepository.findById(movimentacao.getCondutor().getId()).get().getTempoPago().plusHours(movimentacao.getTempo().getHour()).plusMinutes(movimentacao.getTempo().getMinute()));
        }

        this.movimentacaoRepository.save(movimentacao);
    }
}
