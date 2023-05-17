package br.com.uniamerica.estacionamento.entity;

public enum Tipo {
    CARRO(1, "Carro"),
    MOTO(2, "Moto"),
    VAN(3, "Van");

    public final int valor;
    public final String valor2;
    private Tipo(int valor, String valor2){
        this.valor=valor;
        this.valor2=valor2;
    }
}
