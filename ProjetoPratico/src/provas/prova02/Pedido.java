package provas.prova02;

import java.util.ArrayList;

public class Pedido {

    private Cliente comprador;
    private Endereco entrega;
    private ArrayList<ItemCardapio> itens;

    public Pedido(Cliente comprador, String rua, int numero) {
        setComprador(comprador);
        this.entrega = new Endereco(rua, numero);
        this.itens = new ArrayList<>();
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        if (comprador == null) {
            throw new IllegalArgumentException("Comprador obrigatório");
        }
        this.comprador = comprador;
    }

    public String getRuaEntrega() {
        return entrega.getRua();
    }

    public int getNumeroEntrega() {
        return entrega.getNumero();
    }

    public ArrayList<ItemCardapio> getItens() {
        return itens;
    }

    public void adicionarItem(ItemCardapio i) {
        if (i == null) {
            throw new IllegalArgumentException("Item inválido");
        }

        // Forma 1:
        if (itens.contains(i)) {
            throw new IllegalStateException("Item já incluído no pedido");
        }

        // Forma 2:
        for (ItemCardapio item : itens) {
            if (item.getCodigo() == i.getCodigo()) {
                throw new IllegalStateException("Item já incluído no pedido");
            }
        }

        if (itens.size() >= 8) {
            throw new IllegalStateException("Pedido lotado");
        }

        itens.add(i);
    }

    public void removerItem(ItemCardapio i) {
        if (!itens.remove(i)) {
            throw new IllegalStateException("Item não encontrado no pedido");
        }
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (ItemCardapio item : itens) {
            valorTotal = valorTotal + item.calcularPreco();
        }
        return valorTotal;
    }

    public double calcularTotalImpostos() {
        double totalImposto = 0;
        for (ItemCardapio it : itens) {
            if (it instanceof Tributavel) {
                totalImposto = totalImposto + ((Tributavel) it).calcularImposto();
            }
        }
        return totalImposto;
    }
}
