package provas.prova02;

public class Bebida extends ItemCardapio implements Tributavel {

    private int unidades;

    public Bebida(String descricao, double precoBase, int unidades) {
        if (unidades <= 0) {
            throw new IllegalArgumentException("Quantidade de unidades inválida");
        }
        super(descricao, precoBase);
        this.unidades = unidades;
    }

    public int getUnidades() {
        return unidades;
    }

    @Override
    public double calcularPreco() {
        double preco = 0;
        preco = getPrecoBase() * unidades;
        if (unidades >= 6) {
            preco = preco - (preco * 0.15);
        }
        return preco;
    }

    @Override
    public double calcularImposto() {
        return calcularPreco() * 0.12;
    }
}
