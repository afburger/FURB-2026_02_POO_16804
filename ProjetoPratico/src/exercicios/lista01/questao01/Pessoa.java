package exercicios.lista01.questao01;

public class Pessoa {

    double peso;
    double altura;

    double calcularImc() {
        return peso / (altura * altura);
    }

}
