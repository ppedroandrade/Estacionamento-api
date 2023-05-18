package br.com.uniamerica.estacionamento.config;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class ValidaCPF {

    // Método para verificar se um CPF é válido
    public boolean isCPF(String CPF) {
        // Considera-se erro CPF's formados por uma sequência de números iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversão de tipo (int)
        try {
            // Cálculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                // Converte o i-ésimo caractere do CPF em um número:
                // Por exemplo, transforma o caractere '0' no inteiro 0
                // (48 é a posição de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char)(r + 48); // Converte no respectivo caractere numérico
            }

            // Cálculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char)(r + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException erro) {
            return false;
        }
    }

    // Método para formatar um CPF no formato padrão com pontos e hífen
    public String imprimeCPF(String CPF) {
        return CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11);
    }
}
