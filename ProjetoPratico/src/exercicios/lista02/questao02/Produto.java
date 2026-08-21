package exercicios.lista02.questao02;

public class Produto {

    private String nome;
    private double preco;
    private int estoque;

    public void vender(int quantidade) {
        if (this.estoque > quantidade) {
            this.estoque = this.estoque - quantidade;
        } else {
            System.out.println("Quantidade informada é inválida");
        } 
    }

    public void repor(int quantidade) {
        if (quantidade > 0) {
            this.estoque = this.estoque + quantidade;
        } else {
            System.out.println("Quantidade informada é inválida");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        } else {
            System.out.println("Preço informado é inválido");
        }
    }

    public int getEstoque() {
        return estoque;
    }
    
}
