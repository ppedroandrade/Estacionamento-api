package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfiguracaoService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public void cadastraConfiguracao(Configuracao configuracao){
        if("".equals(configuracao.getVagasVan()) || "".equals(configuracao.getVagasCarro()) || "".equals(configuracao.getVagasMoto())){
            throw new RuntimeException("Vagas vazias detectadas (devem conter!)");
        }
        if("".equals(configuracao.getValorHora())){
            throw new RuntimeException("ValorHora de configuração esta vazio (deve conter!)");
        }
        this.configuracaoRepository.save(configuracao);
    }

    @Transactional
    public void atualizaConfiguracao(final Long id, Configuracao configuracao){
        final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);
        if(configuracaoBanco==null || !configuracaoBanco.getId().equals(configuracao.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if("".equals(configuracao.getVagasVan()) || "".equals(configuracao.getVagasCarro()) || "".equals(configuracao.getVagasMoto())){
            throw new RuntimeException("Vagas vazias detectadas (devem conter!)");
        }
        if("".equals(configuracao.getValorHora())){
            throw new RuntimeException("ValorHora de configuração esta vazio (deve conter!)");
        }
        this.configuracaoRepository.save(configuracao);
    }
}
