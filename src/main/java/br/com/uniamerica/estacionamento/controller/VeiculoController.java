package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private VeiculoService veiculoService;

    // Busca um veículo pelo ID na URL
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        return veiculo==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(veiculo);
    }

    // Busca um veículo pelo ID nos parâmetros da requisição
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        return veiculo==null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(veiculo);
    }

    // Retorna a lista completa de veículos
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.veiculoRepository.findAll());
    }

    // Retorna a lista de veículos ativos
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        List<Veiculo> veiculos = veiculoRepository.findByAtivo();
        return ResponseEntity.ok(veiculos);
    }

    // Cadastra um novo veículo
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Veiculo veiculo){
        try {
            this.veiculoService.cadastraVeiculo(veiculo);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }

    // Atualiza um veículo existente
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Veiculo veiculo){
        try {
            this.veiculoService.atualizaVeiculo(id, veiculo);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    // Deleta um veículo pelo ID nos parâmetros da requisição
    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);
        try{
            this.veiculoRepository.delete(veiculoBanco);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(DataIntegrityViolationException e){
            veiculoBanco.setAtivo(false);
            this.veiculoRepository.save(veiculoBanco);
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }
}
