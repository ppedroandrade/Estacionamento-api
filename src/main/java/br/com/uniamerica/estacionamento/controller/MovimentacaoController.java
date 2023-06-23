package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.config.Recibo;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@RequestMapping(value = "/api/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao==null ? ResponseEntity.badRequest().body("Nennhum valor encontrado") : ResponseEntity.ok(movimentacao);
    }
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") :  ResponseEntity.ok(movimentacao);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){return ResponseEntity.ok(this.movimentacaoRepository.findAll());}
    @GetMapping("/aberta")
    public ResponseEntity<?> findByAberta(){return ResponseEntity.ok(this.movimentacaoRepository.findByAberta());}


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Movimentacao movimentacao){
        try{
            this.movimentacaoService.cadastraMovimentacao(movimentacao);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok(Recibo.gerar(movimentacao.getEntrada(), movimentacao.getSaida(), movimentacaoRepository.findById(movimentacao.getId()).get().getCondutor().getNome(), movimentacaoRepository.findById(movimentacao.getId()).get().getVeiculo().getPlaca(), movimentacao.getTempo(), movimentacao.getTempoMulta(), movimentacao.getTempoDesconto(), movimentacao.getValorTotal()));
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Movimentacao movimentacao){
        try{
            this.movimentacaoService.atualizaMovimentacao(id, movimentacao);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok(Recibo.gerar(movimentacao.getEntrada(), movimentacao.getSaida(), movimentacaoRepository.findById(movimentacao.getId()).get().getCondutor().getNome(), movimentacaoRepository.findById(movimentacao.getId()).get().getVeiculo().getPlaca(), movimentacao.getTempo(), movimentacao.getTempoMulta(), movimentacao.getTempoDesconto(), movimentacao.getValorTotal()));
    }
    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);
        if(movimentacaoBanco.isAtivo()) {
            movimentacaoBanco.setAtivo(false);
            this.movimentacaoRepository.save(movimentacaoBanco);
            return ResponseEntity.ok("Registro desativado");
        }
        return ResponseEntity.internalServerError().body("Erro no delete, a flag ja está desativada");
    }
}
