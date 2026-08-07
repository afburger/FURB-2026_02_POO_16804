package exemplos.unidade01.classes;

public class App {

    public static void main(String[] args) {
        Carro carro1;
        carro1 = new Carro();

        carro1.ano = 1979;
        carro1.marca = "VW";
        carro1.modelo = "Fusca";

        Carro carro2 = new Carro();
        carro2.ano = 2026;
        carro2.marca = "BYD";
        carro2.modelo = "Dolphin";

        System.out.println("Modelo carro 1: " + carro1.modelo);

        carro2.alterarSituacao(true);

        System.out.println("O carro 2 está com a situação: " + carro2.situacaoAtual());
        
        Carro carro3;
        carro3 = carro1;

        System.out.println("O ano do carro 3 é: " + carro3.ano);


        carro3.ano = 2025;
        carro3.modelo = "Jetta";

        System.out.println("Modelo do carro 1: " + carro1.modelo);
        System.out.println("Ano do carro 1" + carro1.ano);
    }

}
