package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public void cadastraVeiculo(Veiculo veiculo){
        if("".equals(veiculo.getPlaca())){
            throw new RuntimeException("O veiculo não possui placa (deve conter!)");
        }
        if("".equals(veiculo.getModelo().getNome())){
            throw new RuntimeException("O modelo de veiculo não possui nome (deve conter!)");
        }
        if("".equals(veiculo.getModelo().getMarca().getNome())){
            throw new RuntimeException("A marca de modelo de veiculo não posui nome (deve conter!)");
        }
        if(veiculo.getPlaca().length()>10){
            throw new RuntimeException("A placa de viculo excedeu o limite (10 caracteres!)");
        }
        if(veiculo.getModelo().getNome().length()>50){
            throw new RuntimeException("O nome de modelo de veiculo excedeu o limite (10 caracteres!)");
        }
        if(veiculo.getModelo().getMarca().getNome().length()>50){
            throw new RuntimeException("O nome de marca de modelo de veiculo excedeu o limite (50 caracteres!)");
        }
        if("".equals(veiculo.getAno())){
            throw new RuntimeException("O veiculo não possui um ano (deve conter!)");
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
        if("".equals(veiculo.getPlaca())){
            throw new RuntimeException("O veiculo não possui placa (deve conter!)");
        }
        if("".equals(veiculo.getModelo().getNome())){
            throw new RuntimeException("O modelo de veiculo não possui nome (deve conter!)");
        }
        if("".equals(veiculo.getModelo().getMarca().getNome())){
            throw new RuntimeException("A marca de modelo de veiculo não posui nome (deve conter!)");
        }
        if(veiculo.getCor()==null){
            throw new RuntimeException("O veiculo não possui uma cor (deve conter!)");
        }
        if(veiculo.getTipo()==null){
            throw new RuntimeException("O veiculo não possui um tipo (deve conter!)");
        }
        if(veiculo.getPlaca().length()>10){
            throw new RuntimeException("A placa de viculo excedeu o limite (10 caracteres!)");
        }
        if(veiculo.getModelo().getNome().length()>50){
            throw new RuntimeException("O nome de modelo de veiculo excedeu o limite (10 caracteres!)");
        }
        if(veiculo.getModelo().getMarca().getNome().length()>50){
            throw new RuntimeException("O nome de marca de modelo de veiculo excedeu o limite (50 caracteres!)");
        }
        if(veiculoRepository.findByPlaca(veiculo.getPlaca())!=null){
            throw new RuntimeException("A placa do veiculo ja existe");
        }

        this.veiculoRepository.save(veiculo);
    }
}
