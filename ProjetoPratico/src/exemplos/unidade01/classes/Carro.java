package exemplos.unidade01.classes;

public class Carro {

    String marca;
    String modelo;
    int ano;
    String cor;
    int velocidadeAtual;
    boolean situacaoAtual;

    void acelerar() {
        velocidadeAtual = velocidadeAtual + 10;
        // Tem a mesma funcionalidade da linha acima
        // velocidadeAtual += 10;
    }

    void freiar() {
        velocidadeAtual = velocidadeAtual - 10;
    }

    void alterarSituacao(boolean situacao) {
        situacaoAtual = situacao;
    }

    boolean situacaoAtual() {
        return situacaoAtual;
    }

}
