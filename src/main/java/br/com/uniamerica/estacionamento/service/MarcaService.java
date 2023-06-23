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
        if(marca.getId()!=null){
            throw new RuntimeException("O id deve ser gerado pelo banco");
        }
        if("".equals(marca.getNome())){
            throw new RuntimeException("Marca não possui um nome (deve conter!)");
        }
        if(marca.getNome().length()<3 || marca.getNome().length() > 50){
            throw new RuntimeException("Nome da marca está incorreto (de 3 a 50 caracteres!)");
        }
        if(marcaRepository.findByNome(marca.getNome())!=null){
            throw new RuntimeException("O nome já existe");
        }
        this.marcaRepository.save(marca);
    }

    @Transactional
    public void atualizaMarca(final Long id, Marca marca){
        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);
        if(marcaBanco==null){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if("".equals(marca.getNome())){
            throw new RuntimeException("Marca não possui um nome (deve conter!)");
        }
        if(marca.getNome().length()<3 || marca.getNome().length() > 50){
            throw new RuntimeException("Nome da marca está incorreto (de 3 a 50 caracteres!)");
        }
        if(marcaRepository.findByNome(marca.getNome())!=null){
            throw new RuntimeException("O nome já existe");
        }
        if(marca.getCadastro()==null || "".equals(marca.getCadastro())){
            marca.setCadastro(marcaRepository.findById(marca.getId()).get().getCadastro());
        }

        this.marcaRepository.save(marca);
    }
}
