package exercicios.lista01.questao04;

public class Pessoa {

    double peso;
    double altura;
    String nome;

    double calcularImc() {
        return peso / (altura * altura);
    }

    void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Altura: " + altura);
        System.out.println("Peso: " + peso);
        System.out.println("IMC: " + calcularImc());
    }

}
