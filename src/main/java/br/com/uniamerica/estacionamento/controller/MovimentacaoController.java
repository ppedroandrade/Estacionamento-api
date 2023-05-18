package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/api/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private MovimentacaoService movimentacaoService;

    // Encontra uma movimentação pelo ID (caminho "/api/movimentacao/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(movimentacao);
    }

    // Encontra uma movimentação pelo ID (requisição GET "/api/movimentacao?id={id}")
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") :  ResponseEntity.ok(movimentacao);
    }

    // Retorna a lista completa de movimentações (requisição GET "/api/movimentacao/lista")
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }

    // Retorna as movimentações abertas (requisição GET "/api/movimentacao/abertas")
    @GetMapping("/abertas")
    public ResponseEntity<?> findByAberta(){
        return ResponseEntity.ok(this.movimentacaoRepository.findByAberta());
    }

    // Cadastra uma nova movimentação (requisição POST "/api/movimentacao")
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Movimentacao movimentacao){
        try{
            this.movimentacaoService.cadastraMovimentacao(movimentacao);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }

    // Edita uma movimentação existente (requisição PUT "/api/movimentacao?id={id}")
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Movimentacao movimentacao){
        try{
            this.movimentacaoService.atuaizaMovimentacao(id, movimentacao);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }

    // Deleta uma movimentação pelo ID (caminho "/api/movimentacao/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaAtivo(@PathVariable("id") final Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        if(movimentacao==null){
            return ResponseEntity.badRequest().body("Não foi possível desativar a flag");
        }
        movimentacao.setAtivo(false);
        movimentacaoRepository.save(movimentacao);
        return ResponseEntity.ok("Flag desativada com sucesso");
    }
}
