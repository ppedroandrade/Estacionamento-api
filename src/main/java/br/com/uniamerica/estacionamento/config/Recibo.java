package br.com.uniamerica.estacionamento.config;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class Recibo {
    public static String gerar(LocalTime entrada, LocalTime saida, String condutor, String placa, LocalTime tempo, Long tempoMulta, LocalTime tempoDesconto, BigDecimal valorTotal){
        if(saida == null){
            return "Registro realizado";
        } else {
            return "-- RECIBO DE MOVIMENTAÇÃO --\n" + "Entrada: " + entrada + "\nSaída: " + saida
                    + "\nNome do Condutor: " + condutor + "\nPlaca do Veículo: " + placa + "\nQuantidade de horas no estacionamento: " + tempo
                    + "\nQuantidade de minutos em multa: " + tempoMulta + "\nQuantidade desconto em horas: " + tempoDesconto
                    + "\nValor total: " + valorTotal + "\nRecibo gerado em: " + LocalDateTime.now();
        }
    }
}
