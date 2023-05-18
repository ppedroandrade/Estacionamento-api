package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional
    public void cadastraModelo(Modelo modelo){
        // Verifica se a marca do modelo possui um nome
        if("".equals(modelo.getMarca().getNome())){
            throw new RuntimeException("A marca de modelo não possui nome (deve conter!)");
        }
        
        // Verifica se o nome da marca do modelo excedeu o limite de caracteres
        if(modelo.getMarca().getNome().length()>50){
            throw new RuntimeException("O nome da marca de modelo excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se o modelo possui um nome
        if("".equals(modelo.getNome())){
            throw new RuntimeException("O modelo não possui um nome (deve conter!)");
        }
        
        // Verifica se o nome do modelo excedeu o limite de caracteres
        if(modelo.getNome().length()>50){
            throw new RuntimeException("Nome de modelo excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se já existe um modelo com o mesmo nome
        if(modeloRepository.findByNome(modelo.getNome())!=null){
            throw new RuntimeException("O nome já existe");
        }
        
        // Salva o modelo no banco de dados
        this.modeloRepository.save(modelo);
    }

    @Transactional
    public void atualizaModelo(final Long id, Modelo modelo){
        // Busca o modelo no banco de dados com base no ID fornecido
        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);
        
        // Verifica se o modelo foi encontrado
        if(modeloBanco==null || !modeloBanco.getId().equals(modelo.getId())){
            throw new RuntimeException("Não foi possível encontrar o registro informado");
        }
        
        // Verifica se a marca do modelo possui um nome
        if("".equals(modelo.getMarca().getNome())){
            throw new RuntimeException("A marca de modelo não possui nome (deve conter!)");
        }
        
        // Verifica se o modelo possui um nome
        if("".equals(modelo.getNome())){
            throw new RuntimeException("O modelo não possui um nome (deve conter!)");
        }
        
        // Verifica se o nome do modelo excedeu o limite de caracteres
        if(modelo.getNome().length()>50){
            throw new RuntimeException("Nome de modelo excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se o nome da marca do modelo excedeu o limite de caracteres
        if(modelo.getMarca().getNome().length()>50){
            throw new RuntimeException("O nome da marca de modelo excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se já existe um modelo com o mesmo nome
        if(modeloRepository.findByNome(modelo.getNome())!=null){
            throw new RuntimeException("O nome já existe");
        }
        
        // Salva as alterações do modelo no banco de dados
        this.modeloRepository.save(modelo);
    }
}
