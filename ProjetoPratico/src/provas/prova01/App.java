package provas.prova01;

public class App {
    public static void main(String[] args) throws Exception {
        Produto p1 = new Produto(99, "Garrafa");
        Produto p2 = new Produto(10, "Notebook", 5);

        p1.adicionar(3);
        p2.remover(1);

        try {
            p2.remover(10);
        } catch (Exception e) {
            System.out.println("Mensagem de erro: " + e.getMessage());
        }

        System.out.println("Total de produtos: " + Produto.getTotalProdutos());

        System.out.println("Quantidade do produto 1: " + p1.getQuantidade());
        System.out.println("Quantidade do produto 2: " + p2.getQuantidade());
    }
}
