package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.config.ValidaCPF;
import br.com.uniamerica.estacionamento.config.ValidaTelefone;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
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
    private ValidaTelefone validaTelefone;
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public void cadastraMovimentacao(Movimentacao movimentacao){
        // Verifica se o veículo da movimentação possui uma placa
        if(movimentacao.getVeiculo().getPlaca()==null){
            throw new RuntimeException("O veículo da movimentação não possui placa (deve conter!)");
        }
        
        // Verifica se a placa do veículo da movimentação excedeu o limite de caracteres
        if(movimentacao.getVeiculo().getPlaca().length()>10){
            throw new RuntimeException("A placa do veículo da movimentação excedeu o limite (10 caracteres!)");
        }
        
        // Verifica se o veículo da movimentação possui um nome de modelo
        if(movimentacao.getVeiculo().getModelo().getNome()==null){
            throw new RuntimeException("O veículo da movimentação não possui nome de modelo (deve conter!)");
        }
        
        // Verifica se o nome de modelo do veículo da movimentação excedeu o limite de caracteres
        if(movimentacao.getVeiculo().getModelo().getNome().length()>50){
            throw new RuntimeException("O nome de modelo de veículo de movimentação excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se o veículo da movimentação possui um nome de marca em modelo
        if(movimentacao.getVeiculo().getModelo().getMarca().getNome()==null){
            throw new RuntimeException("O veículo da movimentação não possui nome de marca em modelo (deve conter!)");
        }
        
        // Verifica se o nome de marca de modelo do veículo da movimentação excedeu o limite de caracteres
        if(movimentacao.getVeiculo().getModelo().getMarca().getNome().length()>50){
            throw new RuntimeException("O nome de marca de modelo de veículo de movimentação excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se o veículo possui um ano
        if("".equals(movimentacao.getVeiculo().getAno())){
            throw new RuntimeException("O veículo não possui um ano (deve conter!)");
        }
        
        // Verifica se o condutor da movimentação possui um nome
        if("".equals(movimentacao.getCondutor().getNome())){
            throw new RuntimeException("O condutor da movimentação não possui um nome (deve conter!)");
        }
        
        // Verifica se o nome de condutor de movimentação excedeu o limite de caracteres
        if(movimentacao.getCondutor().getNome().length()>100){
            throw new RuntimeException("O nome de condutor de movimentação excedeu o limite (100 caracteres)!");
        }
        
        // Verifica se o CPF do condutor está válido
        if(!this.validaCPF.isCPF(movimentacao.getCondutor().getCpf())){
            throw new RuntimeException("O CPF está inválido");
        }
        
        // Verifica se o telefone do condutor está válido
        if(!this.validaTelefone.validaTelefone(movimentacao.getCondutor().getTelefone())){
            throw new RuntimeException("O telefone está inválido");
        }
        
        // Verifica se a movimentação possui uma entrada
        if("".equals(movimentacao.getEntrada())){
            throw new RuntimeException("A movimentação não possui uma entrada (deve conter!)");
        }
        
        // Verifica se o veículo da movimentação possui uma cor
        if(movimentacao.getVeiculo().getCor()==null){
            throw new RuntimeException("O veículo da movimentação não possui uma cor (deve conter!)");
        }
        
        // Verifica se o veículo da movimentação possui um tipo
        if(movimentacao.getVeiculo().getTipo()==null){
            throw new RuntimeException("O veículo da movimentação não possui um tipo (deve conter!)");
        }
        
        // Calcula o tempo de permanência caso a movimentação já tenha uma saída
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond());
            movimentacao.setTempo(tempo);
        }
        
        // Calcula o valor total caso o tempo de permanência e o valor por hora estejam definidos
        if(movimentacao.getTempo()!=null) {
            movimentacao.setValorHora(configuracaoRepository.findValorHora());
            BigDecimal valorTotal = configuracaoRepository.findValorHora().multiply(new BigDecimal(movimentacao.getTempo().getHour()));
            movimentacao.setValorTotal(valorTotal);
        }

        this.movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void atuaizaMovimentacao(final Long id, Movimentacao movimentacao){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);
        
        // Verifica se a movimentação a ser atualizada existe no banco de dados
        if(movimentacaoBanco==null || !movimentacaoBanco.getId().equals(movimentacao.getId())){
            throw new RuntimeException("Não foi possível encontrar o registro informado");
        }
        
        // Verifica as mesmas condições de validação aplicadas no método cadastraMovimentacao
        // (os comentários foram omitidos para evitar repetição)
        if(movimentacao.getVeiculo().getPlaca()==null){
            throw new RuntimeException("O veículo da movimentação não possui placa (deve conter!)");
        }
        if(movimentacao.getVeiculo().getModelo().getNome()==null){
            throw new RuntimeException("O veículo da movimentação não possui nome de modelo (deve conter!)");
        }
        if(movimentacao.getVeiculo().getModelo().getMarca().getNome()==null){
            throw new RuntimeException("O veículo da movimentação não possui nome de marca em modelo (deve conter!)");
        }
        if(movimentacao.getVeiculo().getAno() == 0){
            throw new RuntimeException("O veículo não possui um ano (deve conter!)");
        }
        if(movimentacao.getVeiculo().getPlaca().length()>10){
            throw new RuntimeException("A placa do veículo da movimentação excedeu o limite (10 caracteres!)");
        }
        if(movimentacao.getVeiculo().getModelo().getNome().length()>50){
            throw new RuntimeException("O nome de modelo de veículo de movimentação excedeu o limite (50 caracteres!)");
        }
        if(movimentacao.getVeiculo().getModelo().getMarca().getNome().length()>50){
            throw new RuntimeException("O nome de marca de modelo de veículo de movimentação excedeu o limite (50 caracteres!)");
        }
        if(movimentacao.getCondutor().getNome().length()>100){
            throw new RuntimeException("O nome de condutor de movimentação excedeu o limite (100 caracteres)!");
        }
        if(!this.validaCPF.isCPF(movimentacao.getCondutor().getCpf())){
            throw new RuntimeException("O CPF está inválido");
        }
        if(!this.validaTelefone.validaTelefone(movimentacao.getCondutor().getTelefone())){
            throw new RuntimeException("O telefone está inválido");
        }
        if(movimentacao.getVeiculo().getCor()==null){
            throw new RuntimeException("O veículo da movimentação não possui uma cor (deve conter!)");
        }
        if(movimentacao.getVeiculo().getTipo()==null){
            throw new RuntimeException("O veículo da movimentação não possui um tipo (deve conter!)");
        }
        if("".equals(movimentacao.getEntrada())){
            throw new RuntimeException("A movimentação não possui uma entrada (deve conter!)");
        }
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond());
            movimentacao.setTempo(tempo);
        }
        if(movimentacao.getTempo()!=null) {
            movimentacao.setValorHora(configuracaoRepository.findValorHora());
            BigDecimal valorTotal = movimentacao.getValorHora().multiply(new BigDecimal(movimentacao.getTempo().getHour()));
            movimentacao.setValorTotal(valorTotal);
        }

        this.movimentacaoRepository.save(movimentacao);
    }
}
