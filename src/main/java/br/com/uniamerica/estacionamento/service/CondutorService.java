package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.config.ValidaCPF;
import br.com.uniamerica.estacionamento.config.ValidaTelefone;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CondutorService {
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private ValidaTelefone validaTelefone;
    @Autowired
    private ValidaCPF validaCPF;

    @Transactional
    public void cadastraCondutor(Condutor condutor){
        // Verifica se o condutor possui um nome
        if(condutor.getNome()==null){
            throw new RuntimeException("Condutor não possui um nome (deve conter!)");
        }
        
        // Valida o número de telefone do condutor
        if(this.validaTelefone.validaTelefone(condutor.getTelefone())){
            throw new RuntimeException("Telefone inválido");
        }
        
        // Valida o CPF do condutor
        if (this.validaCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("CPF do condutor está incorreto");
        }
        
        // Verifica se o nome do condutor excede o limite de caracteres
        if(condutor.getNome().length() > 100){
            throw new RuntimeException("Nome do condutor excedeu o limite (100 caracteres!)");
        }
        
        // Verifica se o CPF já existe no banco de dados
        if(condutorRepository.findByCpf(condutor.getCpf())!=null){
            throw new RuntimeException("O CPF já existe");
        }
        
        // Salva o condutor no banco de dados
        this.condutorRepository.save(condutor);
    }

    @Transactional
    public void atualizaCondutor(final Long id, Condutor condutor){
        // Busca o condutor no banco de dados com base no ID fornecido
        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);
        
        // Verifica se o condutor foi encontrado ou se o ID fornecido é diferente do ID do condutor encontrado
        if(condutorBanco==null || !condutorBanco.getId().equals(condutor.getId())){
            throw new RuntimeException("Não foi possível encontrar o registro informado");
        }
        
        // Verifica se o condutor possui um nome
        if(condutor.getNome()==null){
            throw new RuntimeException("Condutor não possui um nome (deve conter!)");
        }
        
        // Valida o número de telefone do condutor
        if(this.validaTelefone.validaTelefone(condutor.getTelefone())){
            throw new RuntimeException("Telefone inválido");
        }
        
        // Valida o CPF do condutor
        if (this.validaCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("CPF do condutor está incorreto");
        }
        
        // Verifica se o nome do condutor excede o limite de caracteres
        if(condutor.getNome().length() > 100){
            throw new RuntimeException("Nome do condutor excedeu o limite (100 caracteres!)");
        }
        
        // Verifica se o CPF já existe no banco de dados
        if(condutorRepository.findByCpf(condutor.getCpf())!=null){
            throw new RuntimeException("O CPF já existe");
        }
        
        // Salva as alterações do condutor no banco de dados
        this.condutorRepository.save(condutor);
    }
}
