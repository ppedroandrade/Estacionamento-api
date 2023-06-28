package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.config.ValidaTelefone;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/condutor")
public class CondutorController {
    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private CondutorService condutorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(condutor);
    }
    @GetMapping
    public ResponseEntity<?> findByIdReques(@RequestParam("id") final Long id){
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(condutor);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){ return ResponseEntity.ok(this.condutorRepository.findAll());}
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        List<Condutor> condutores = condutorRepository.findByAtivo();
        return ResponseEntity.ok(condutores);
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Condutor condutor) {
        try {
            this.condutorService.cadastraCondutor(condutor);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Condutor condutor){
        try{
            this.condutorService.atualizaCondutor(id, condutor);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <?> deletar(@PathVariable("id") final Long id){
        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);
        try{
            this.condutorRepository.delete(condutorBanco);
        }
        catch(RuntimeException e){
            if(condutorBanco.isAtivo()) {
                condutorBanco.setAtivo(false);
                this.condutorRepository.save(condutorBanco);
                return ResponseEntity.internalServerError().body("Erro no delete, flag desativada!");
            }
            return ResponseEntity.internalServerError().body("Erro no delete, a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }
}