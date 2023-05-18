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
    public void cadastraVeiculo(Veiculo veiculo) {
        // Verifica se o veículo possui placa
        if ("".equals(veiculo.getPlaca())) {
            throw new RuntimeException("O veículo não possui placa (deve conter!)");
        }

        // Verifica se o modelo de veículo possui nome
        if ("".equals(veiculo.getModelo().getNome())) {
            throw new RuntimeException("O modelo de veículo não possui nome (deve conter!)");
        }

        // Verifica se a marca do modelo de veículo possui nome
        if ("".equals(veiculo.getModelo().getMarca().getNome())) {
            throw new RuntimeException("A marca de modelo de veículo não possui nome (deve conter!)");
        }

        // Verifica se a placa do veículo excedeu o limite de caracteres
        if (veiculo.getPlaca().length() > 10) {
            throw new RuntimeException("A placa de veículo excedeu o limite (10 caracteres!)");
        }

        // Verifica se o nome do modelo de veículo excedeu o limite de caracteres
        if (veiculo.getModelo().getNome().length() > 50) {
            throw new RuntimeException("O nome de modelo de veículo excedeu o limite (50 caracteres!)");
        }

        // Verifica se o nome da marca do modelo de veículo excedeu o limite de caracteres
        if (veiculo.getModelo().getMarca().getNome().length() > 50) {
            throw new RuntimeException("O nome de marca de modelo de veículo excedeu o limite (50 caracteres!)");
        }

        // Verifica se o veículo possui um ano
        if ("".equals(veiculo.getAno())) {
            throw new RuntimeException("O veículo não possui um ano (deve conter!)");
        }

        // Verifica se o veículo possui uma cor
        if (veiculo.getCor() == null) {
            throw new RuntimeException("O veículo não possui uma cor (deve conter!)");
        }

        // Verifica se o veículo possui um tipo
        if (veiculo.getTipo() == null) {
            throw new RuntimeException("O veículo não possui um tipo (deve conter!)");
        }

        // Verifica se a placa do veículo já existe no banco de dados
        if (veiculoRepository.findByPlaca(veiculo.getPlaca()) != null) {
            throw new RuntimeException("A placa do veículo já existe");
        }

        this.veiculoRepository.save(veiculo);
    }

    @Transactional
    public void atualizaVeiculo(final Long id, Veiculo veiculo) {
        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);

        // Verifica se o veículo existe no banco de dados
        if (veiculoBanco == null || !veiculoBanco.getId().equals(veiculo.getId())) {
            throw new RuntimeException("Não foi possível encontrar o registro informado");
        }

        // Verifica se o veículo possui placa
        if ("".equals(veiculo.getPlaca())) {
            throw new RuntimeException("O veículo não possui placa (deve conter!)");
        }

        // Verifica se o modelo de veículo possui nome
        if ("".equals(veiculo.getModelo().getNome())) {
            throw new RuntimeException("O modelo de veículo não possui nome (deve conter!)");
        }

        // Verifica se a marca do modelo de veículo possui nome
        if ("".equals(veiculo.getModelo().getMarca().getNome())) {
            throw new RuntimeException("A marca de modelo de veículo não possui nome (deve conter!)");
        }

        // Verifica se o veículo possui uma cor
        if (veiculo.getCor() == null) {
            throw new RuntimeException("O veículo não possui uma cor (deve conter!)");
        }

        // Verifica se o veículo possui um tipo
        if (veiculo.getTipo() == null) {
            throw new RuntimeException("O veículo não possui um tipo (deve conter!)");
        }

        // Verifica se a placa do veículo excedeu o limite de caracteres
        if (veiculo.getPlaca().length() > 10) {
            throw new RuntimeException("A placa de veículo excedeu o limite (10 caracteres!)");
        }

        // Verifica se o nome do modelo de veículo excedeu o limite de caracteres
        if (veiculo.getModelo().getNome().length() > 50) {
            throw new RuntimeException("O nome de modelo de veículo excedeu o limite (50 caracteres!)");
        }

        // Verifica se o nome da marca do modelo de veículo excedeu o limite de caracteres
        if (veiculo.getModelo().getMarca().getNome().length() > 50) {
            throw new RuntimeException("O nome de marca de modelo de veículo excedeu o limite (50 caracteres!)");
        }

        // Verifica se a placa do veículo já existe no banco de dados
        if (veiculoRepository.findByPlaca(veiculo.getPlaca()) != null) {
            throw new RuntimeException("A placa do veículo já existe");
        }

        this.veiculoRepository.save(veiculo);
    }
}
