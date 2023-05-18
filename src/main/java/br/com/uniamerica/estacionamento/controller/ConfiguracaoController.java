package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/configuracao")
public class ConfiguracaoController {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private ConfiguracaoService configuracaoService;

    // Método para obter uma configuração por ID usando caminho na URL
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") Long id){
        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        return configuracao==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(configuracao);
    }

    // Método para obter uma configuração por ID usando parâmetro de requisição
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") Long id){
        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        return configuracao==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(configuracao);
    }

    // Método para obter configurações ativas
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        List<Configuracao> configuracao = configuracaoRepository.findByAtivo();
        return ResponseEntity.ok(configuracao);
    }

    // Método para cadastrar uma configuração
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Configuracao configuracao){
        try {
            this.configuracaoService.cadastraConfiguracao(configuracao);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }
    
    // Método para editar uma configuração
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") Long id, @RequestBody Configuracao configuracao){
        try {
            this.configuracaoService.atualizaConfiguracao(id, configuracao);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }
}
