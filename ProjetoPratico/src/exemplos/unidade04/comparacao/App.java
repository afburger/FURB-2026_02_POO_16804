package exemplos.unidade04.comparacao;

public class App {


    public static void main(String[] args) {
        Pessoa p1 = new Pessoa("André Felipe Bürger", "123456");

        Pessoa p2 = new Pessoa("André Felipe Bürger", "123456");

        if (p1.equals(p2)) {
            System.out.println("São iguais");
        } else {
            System.out.println("São diferentes");
        }
    }

}
