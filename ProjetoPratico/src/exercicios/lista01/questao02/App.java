package exercicios.lista01.questao02;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pessoa usuario = new Pessoa();

        System.out.println("Informe a sua altura: ");
        usuario.altura = scan.nextDouble();

        System.out.println("Informe o seu peso: ");
        usuario.peso = scan.nextDouble();

        System.out.println("O seu IMC é: " + usuario.calcularImc());
    }

}
