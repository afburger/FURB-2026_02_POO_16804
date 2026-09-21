package provas.prova01;

public class Produto {

    private static int totalProdutos;

    private int codigo;
    private String nome;
    private int quantidade;

    public Produto(int codigo, String nome) {
        this(codigo, nome, 0);
    }

    public Produto(int codigo, String nome, int quantidadeInicial) {
        if (quantidadeInicial < 0) {
            throw new IllegalArgumentException("Quantidade inicial inválida");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidadeInicial;

        totalProdutos++;
    }


    public void adicionar(int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade de entrada inválida");
        }
        quantidade = quantidade + qtd;
    }

    public void remover(int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade de saída inválida");
        }
        if (qtd > quantidade) {
            throw new IllegalStateException("Estoque insuficiente");
        }
        quantidade = quantidade - qtd;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static int getTotalProdutos() {
        return totalProdutos;
    }
}
