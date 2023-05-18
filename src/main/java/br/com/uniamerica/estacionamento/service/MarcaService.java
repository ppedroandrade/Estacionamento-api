package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public void cadastraMarca(Marca marca){
        // Verifica se o nome da marca está vazio
        if("".equals(marca.getNome())){
            throw new RuntimeException("Marca não possui um nome (deve conter!)");
        }
        
        // Verifica se o nome da marca excedeu o limite de caracteres
        if(marca.getNome().length() > 50){
            throw new RuntimeException("Nome da marca excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se já existe uma marca com o mesmo nome
        if(marcaRepository.findByNome(marca.getNome())!=null){
            throw new RuntimeException("O nome já existe");
        }
        
        // Salva a marca no banco de dados
        this.marcaRepository.save(marca);
    }

    @Transactional
    public void atualizaMarca(final Long id, Marca marca){
        // Busca a marca no banco de dados com base no ID fornecido
        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);
        
        // Verifica se a marca foi encontrada
        if(marcaBanco==null){
            throw new RuntimeException("Não foi possível encontrar o registro informado");
        }
        
        // Verifica se o nome da marca está vazio
        if("".equals(marca.getNome())){
            throw new RuntimeException("Marca não possui um nome (deve conter!)");
        }
        
        // Verifica se o nome da marca excedeu o limite de caracteres
        if(marca.getNome().length() > 50){
            throw new RuntimeException("Nome da marca excedeu o limite (50 caracteres!)");
        }
        
        // Verifica se já existe uma marca com o mesmo nome
        if(marcaRepository.findByNome(marca.getNome())!=null){
            throw new RuntimeException("O nome já existe");
        }
        
        // Salva as alterações da marca no banco de dados
        this.marcaRepository.save(marca);
    }
}
