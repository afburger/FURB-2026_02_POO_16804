package provas.prova02;

public class Prato extends ItemCardapio {

    private int quantidade;

    public Prato(String descricao, double precoBase, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de pratos inválida");
        }
        super(descricao, precoBase);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * quantidade;
    }

}
