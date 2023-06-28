package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.service.MarcaService;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/marca")
public class MarcaController {
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        return marca==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(marca);
        }
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        return marca==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(marca);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){return ResponseEntity.ok(this.marcaRepository.findAll());}
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        List<Marca> marcas = marcaRepository.findByAtivo();
        return ResponseEntity.ok(marcas);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marca){
        try {
            this.marcaService.cadastraMarca(marca);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Marca marca){
        try{
            this.marcaService.atualizaMarca(id, marca);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }
    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);
        try{
            this.marcaRepository.delete(marcaBanco);
        }
        catch(RuntimeException e){
            if(marcaBanco.isAtivo()) {
                marcaBanco.setAtivo(false);
                this.marcaRepository.save(marcaBanco);
                return ResponseEntity.internalServerError().body("Erro no delete, flag desativada!");
            }
            return ResponseEntity.internalServerError().body("Erro no delete, a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }
}
