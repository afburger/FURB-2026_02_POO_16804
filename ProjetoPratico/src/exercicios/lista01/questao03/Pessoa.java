package exercicios.lista01.questao03;

public class Pessoa {

    double peso;
    double altura;

    double calcularImc() {
        return peso / (altura * altura);
    }

}
