package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.config.ValidaPlaca;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ValidaPlaca validaPlaca;
    @Transactional
    public void cadastraVeiculo(Veiculo veiculo){
        if(veiculo.getId()!=null){
            throw new RuntimeException("O id deve ser gerado pelo banco");
        }
        if(validaPlaca.validarPlaca(veiculo.getPlaca())!=true){
            throw new RuntimeException("O veiculo possui uma placa irregular");
        }
        if("".equals(veiculo.getAno())){
            throw new RuntimeException("O veiculo não possui um ano (deve conter!)");
        }
        if(veiculo.getAno()<=1886 || veiculo.getAno()>=2024){
            throw new RuntimeException("O veiculo está com um ano impossivel");
        }
        if(veiculo.getCor()==null){
            throw new RuntimeException("O veiculo não possui uma cor (deve conter!)");
        }
        if(veiculo.getTipo()==null){
            throw new RuntimeException("O veiculo não possui um tipo (deve conter!)");
        }
        if(veiculoRepository.findByPlaca(veiculo.getPlaca())!=null){
            throw new RuntimeException("A placa do veiculo ja existe");
        }
        this.veiculoRepository.save(veiculo);
    }

    @Transactional
    public void atualizaVeiculo(final Long id, Veiculo veiculo){
        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);
        if(veiculoBanco==null || !veiculoBanco.getId().equals(veiculo.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if(validaPlaca.validarPlaca(veiculo.getPlaca())!=true){
            throw new RuntimeException("O veiculo possui uma placa irregular");
        }
        if("".equals(veiculo.getAno())){
            throw new RuntimeException("O veiculo não possui um ano (deve conter!)");
        }
        if(veiculo.getAno()<=1886 || veiculo.getAno()>=2024){
            throw new RuntimeException("O veiculo está com um ano impossivel");
        }
        if(veiculo.getCor()==null){
            throw new RuntimeException("O veiculo não possui uma cor (deve conter!)");
        }
        if(veiculo.getTipo()==null){
            throw new RuntimeException("O veiculo não possui um tipo (deve conter!)");
        }
        if(veiculoRepository.findByPlaca(veiculo.getPlaca())!=null || veiculo.getPlaca()==null){
            veiculo.setPlaca(veiculoRepository.findById(veiculo.getId()).get().getPlaca());
        }
        if(veiculo.getCadastro()==null || "".equals(veiculo.getCadastro())){
            veiculo.setCadastro(veiculoRepository.findById(veiculo.getId()).get().getCadastro());
        }

        this.veiculoRepository.save(veiculo);
    }
}