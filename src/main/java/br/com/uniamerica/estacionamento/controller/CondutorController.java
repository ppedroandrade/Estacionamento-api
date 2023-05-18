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

    // Método para obter um condutor por ID usando caminho na URL
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(condutor);
    }

    // Método para obter um condutor por ID usando parâmetro de requisição
    @GetMapping
    public ResponseEntity<?> findByIdReques(@RequestParam("id") final Long id){
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(condutor);
    }

    // Método para obter a lista completa de condutores
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){ return ResponseEntity.ok(this.condutorRepository.findAll());}

    // Método para obter condutores ativos
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        List<Condutor> condutores = condutorRepository.findByAtivo();
        return ResponseEntity.ok(condutores);
    }

    // Método para cadastrar um condutor
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Condutor condutor) {
        try {
            this.condutorService.cadastraCondutor(condutor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }

    // Método para editar um condutor
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Condutor condutor){
        try{
            this.condutorService.atualizaCondutor(id, condutor);
        } catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    // Método para deletar um condutor
    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);
        try{
            this.condutorRepository.delete(condutorBanco);
            return ResponseEntity.ok("Registro deletado");
        } catch(DataIntegrityViolationException e){
            condutorBanco.setAtivo(false);
            this.condutorRepository.save(condutorBanco);
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }
}
