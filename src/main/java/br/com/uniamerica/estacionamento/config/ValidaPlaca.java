package br.com.uniamerica.estacionamento.config;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidaPlaca {
    private static final String mercosulMascara = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}";

    public static boolean validarPlaca(String placa) {
        Pattern pattern = Pattern.compile(mercosulMascara);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }
}
