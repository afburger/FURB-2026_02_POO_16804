package provas.prova02;

public abstract class ItemCardapio {

    private static int totalItens;
    private int codigo;
    private String descricao;
    private double precoBase;

    public ItemCardapio(String descricao, double precoBase) {
        if (precoBase <= 0) {
            throw new IllegalArgumentException("Preço base inválido");
        }
        this.descricao = descricao;
        this.precoBase = precoBase;
        totalItens++;
        this.codigo = totalItens;
    }

    public static int getTotalItens() {
        return totalItens;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public abstract double calcularPreco();

    @Override
    public String toString() {
        return codigo + " - " + descricao + " - R$ " + calcularPreco();
    }
}
