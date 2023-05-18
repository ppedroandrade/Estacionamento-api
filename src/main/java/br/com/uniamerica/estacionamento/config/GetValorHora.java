package br.com.uniamerica.estacionamento.config;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class GetValorHora {
    @Autowired
    private Configuracao configuracao1;
    // Método para obter o valor da hora de estacionamento
    public BigDecimal getValorHora() {
        // Criação de uma variável local "configuracao" e atribuição do valor do campo "configuracao1"
        Configuracao configuracao = configuracao1;
        // Retorno do valor da propriedade "valorHora" do objeto "configuracao"
        return configuracao.getValorHora();
    }
}
