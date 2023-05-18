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
        // Verifica se as vagas de van, carro e moto estão vazias
        if("".equals(configuracao.getVagasVan()) || "".equals(configuracao.getVagasCarro()) || "".equals(configuracao.getVagasMoto())){
            throw new RuntimeException("Vagas vazias detectadas (devem conter!)");
        }
        
        // Verifica se o valor da hora da configuração está vazio
        if("".equals(configuracao.getValorHora())){
            throw new RuntimeException("ValorHora de configuração está vazio (deve conter!)");
        }
        
        // Salva a configuração no banco de dados
        this.configuracaoRepository.save(configuracao);
    }

    @Transactional
    public void atualizaConfiguracao(final Long id, Configuracao configuracao){
        // Busca a configuração no banco de dados com base no ID fornecido
        final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);
        
        // Verifica se a configuração foi encontrada ou se o ID fornecido é diferente do ID da configuração encontrada
        if(configuracaoBanco==null || !configuracaoBanco.getId().equals(configuracao.getId())){
            throw new RuntimeException("Não foi possível encontrar o registro informado");
        }
        
        // Verifica se as vagas de van, carro e moto estão vazias
        if("".equals(configuracao.getVagasVan()) || "".equals(configuracao.getVagasCarro()) || "".equals(configuracao.getVagasMoto())){
            throw new RuntimeException("Vagas vazias detectadas (devem conter!)");
        }
        
        // Verifica se o valor da hora da configuração está vazio
        if("".equals(configuracao.getValorHora())){
            throw new RuntimeException("ValorHora de configuração está vazio (deve conter!)");
        }
        
        // Salva as alterações da configuração no banco de dados
        this.configuracaoRepository.save(configuracao);
    }
}
