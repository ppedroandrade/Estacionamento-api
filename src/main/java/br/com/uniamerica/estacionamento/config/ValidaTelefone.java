package br.com.uniamerica.estacionamento.config;

import org.springframework.stereotype.Component;

@Component
public class ValidaTelefone {
    // Método estático para validar um número de telefone
    public static boolean validaTelefone(String tel) {
        // Expressão regular que representa o padrão de um número de telefone brasileiro
        String telBR = "[1-9][0-9][2-9]{2}[0-9]{3}[0-9]{4}";
        // Verifica se o número de telefone corresponde ao padrão definido pela expressão regular
        return tel.matches(telBR);
    }
}
