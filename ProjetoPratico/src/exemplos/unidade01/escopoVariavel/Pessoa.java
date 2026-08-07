package exemplos.unidade01.escopoVariavel;

public class Pessoa {

    double peso;
    double altura;
    double imc;

    double calcularImc() {
        return peso / (altura * altura);
    }

    void exibirInfos() {
        System.out.println("Peso:" + peso);
        double imc = calcularImc();
        System.out.println("Imc" + imc);
    }

}
