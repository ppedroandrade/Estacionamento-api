package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.config.ValidaCPF;
import br.com.uniamerica.estacionamento.config.ValidaTelefone;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

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
        if(condutor.getId()!=null){
            throw new RuntimeException("O id deve ser gerado pelo banco");
        }
        if(condutor.getNome()==null){
            throw new RuntimeException("Condutor não possui um nome (deve conter!)");
        }
        if(!this.validaTelefone.validaTelefone(condutor.getTelefone())){
            throw new RuntimeException("Telefone ínvalido");
        }
        if (this.validaCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf de condutor está incorreto");
        }
        if(condutor.getNome().length()<3 || condutor.getNome().length() > 100){
            throw new RuntimeException("Nome de condutor está errado (de 3 a 100 caracteres!)");
        }
        if(condutorRepository.findByCpf(condutor.getCpf())!=null){
            throw new RuntimeException("O CPF já existe");
        }
        condutor.setTempoPago(LocalTime.of(0, 0, 0));
        condutor.setTempoDesconto(LocalTime.of(0, 0, 0));

        this.condutorRepository.save(condutor);
    }

    @Transactional
    public void atualizaCondutor(final Long id, Condutor condutor){
        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);
        if(condutorBanco==null || !condutorBanco.getId().equals(condutor.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if(condutor.getNome()==null){
            throw new RuntimeException("Condutor não possui um nome (deve conter!)");
        }
        if(!this.validaTelefone.validaTelefone(condutor.getTelefone())){
            throw new RuntimeException("Telefone ínvalido");
        }
        if (this.validaCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf de condutor está incorreto");
        }
        if(condutor.getNome().length()<3 || condutor.getNome().length() > 100){
            throw new RuntimeException("Nome de condutor está errado (de 3 a 100 caracteres!)");
        }
        if(condutor.getCadastro()==null || "".equals(condutor.getCadastro())){
            condutor.setCadastro(condutorRepository.findById(condutor.getId()).get().getCadastro());
        }

        this.condutorRepository.save(condutor);
    }

}
