package exemplos.unidade01.construtores;

public class App {

    public static void main(String[] args) {
        Carro carro1 = new Carro("Kombi", 2015, "Branca");

        System.out.println(carro1.getModelo());

        Carro carro2 = new Carro();

        System.out.println(carro2.getModelo());

        Carro carro3 = new Carro("Dolphin", 2026, "Azul", "BYD");

        System.out.println(carro3.getMarca());
    }

}
