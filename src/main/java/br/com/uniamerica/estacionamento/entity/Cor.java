package br.com.uniamerica.estacionamento.entity;

public enum Cor{
    AMARELO(1, "Amarelo"),
    VERMELHO(2, "Vermelho"),
    ROSA(3, "Rosa"),
    PRATA(4, "Prata"),
    CINZA(5, "Cinza"),
    BRANCO(6, "Branco"),
    PRETO(7, "Preto"),
    AZUL(8, "Azul"),
    BEGE(9, "Bege"),
    VERDE(10, "Verde");

    public final int valor;
    public final String valor2;
    private Cor(int valor, String valor2){
        this.valor=valor;
        this.valor2=valor2;
    }
}
