package exercicios.lista02.questao02;

public class App {

    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setNome("Iphone 17 Pro Max");
        produto.setPreco(8000);

        produto.vender(10);

        produto.repor(50);

        produto.vender(5);
        
        System.out.println("Quantidade em estoque:" + produto.getEstoque());

        produto.vender(10);

        System.out.println("Quantidade em estoque:" + produto.getEstoque());

        produto.setPreco(-1500);

    }

}
