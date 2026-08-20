package exercicios.lista01.questao02;

public class Pessoa {

    double peso;
    double altura;

    double calcularImc() {
        return peso / (altura * altura);
    }

}
