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
        if(modelo.getId()!=null){
            throw new RuntimeException("O id deve ser gerado pelo banco");
        }
        if(modelo.getMarca().getId()==null){
            throw new RuntimeException("O id da marca está incorreto");
        }
        if("".equals(modelo.getNome())){
            throw new RuntimeException("O modelo não possui um nome (deve conter!)");
        }
        if(modelo.getNome().length()<4 || modelo.getNome().length()>50){
            throw new RuntimeException("Nome de modelo excedeu o limite (entre 4 á 50 caracteres!)");
        }
        if(modeloRepository.findByNome(modelo.getNome())!=null){
            throw new RuntimeException("O nome ja existe");
        }
        this.modeloRepository.save(modelo);
    }

    @Transactional
    public void atualizaModelo(final Long id, Modelo modelo){
        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);
        if(modeloBanco==null || !modeloBanco.getId().equals(modelo.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if("".equals(modelo.getNome())){
            throw new RuntimeException("O modelo não possui um nome (deve conter!)");
        }
        if(modelo.getNome().length()<4 || modelo.getNome().length()>50){
            throw new RuntimeException("Nome de modelo excedeu o limite (entre 4 á 50 caracteres!)");
        }
        if(modeloRepository.findByNome(modelo.getNome())!=null){
            throw new RuntimeException("O nome ja existe");
        }
        if(modelo.getCadastro()==null || "".equals(modelo.getCadastro())){
            modelo.setCadastro(modeloRepository.findById(modelo.getId()).get().getCadastro());
        }

        this.modeloRepository.save(modelo);
    }
}

