// Cada opção de cor é definida como uma constante enum, por exemplo, AMARELO, VERMELHO, ROSA, etc.
// Cada constante enum tem dois valores associados: valor (valor numérico da cor) e valor2 (nome da cor).
// O construtor privado Cor(int valor, String valor2) é responsável por definir os valores das constantes enum.
// As constantes enum são seguidas por um ponto e vírgula.
// A variável valor é declarada como final, indicando que seu valor não pode ser alterado após a inicialização.
// A variável valor2 também é declarada como final, indicando que seu valor não pode ser alterado após a inicialização.

package br.com.uniamerica.estacionamento.entity;

public enum Cor {
    AMARELO(1, "Amarelo"), // Opção de cor: Amarelo, valor: 1
    VERMELHO(2, "Vermelho"), // Opção de cor: Vermelho, valor: 2
    ROSA(3, "Rosa"), // Opção de cor: Rosa, valor: 3
    PRATA(4, "Prata"), // Opção de cor: Prata, valor: 4
    CINZA(5, "Cinza"), // Opção de cor: Cinza, valor: 5
    BRANCO(6, "Branco"), // Opção de cor: Branco, valor: 6
    PRETO(7, "Preto"), // Opção de cor: Preto, valor: 7
    AZUL(8, "Azul"), // Opção de cor: Azul, valor: 8
    BEGE(9, "Bege"), // Opção de cor: Bege, valor: 9
    VERDE(10, "Verde"); // Opção de cor: Verde, valor: 10

    public final int valor; // Valor numérico da cor
    public final String valor2; // Nome da cor

    // Construtor do enum Cor
    private Cor(int valor, String valor2) {
        this.valor = valor;
        this.valor2 = valor2;
    }
}
