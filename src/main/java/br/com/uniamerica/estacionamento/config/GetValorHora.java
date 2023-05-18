package br.com.uniamerica.estacionamento.config;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class GetValorHora {
    @Autowired
    private Configuracao configuracao1;
    public BigDecimal getValorHora() {
        Configuracao configuracao = configuracao1;
        return configuracao.getValorHora();
    }
}
