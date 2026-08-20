package exercicios.lista01.questao03;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        Pessoa p3 = new Pessoa();

        System.out.println("--- Pessoa 1 ---");
        System.out.println("Informe a altura da pessoa 1: ");
        p1.altura = scan.nextDouble();

        System.out.println("Informe o peso da pessoa 1:");
        p1.peso = scan.nextDouble();      

        System.out.println("O IMC da pessoa 1 é: " + p1.calcularImc());

        System.out.println("--- Pessoa 2 ---");
        System.out.println("Informe a altura da pessoa 2: ");
        p2.altura = scan.nextDouble();

        System.out.println("Informe o peso da pessoa 2:");
        p2.peso = scan.nextDouble();

        System.out.println("O IMC da pessoa 2 é: " + p2.calcularImc());

        System.out.println("--- Pessoa 3 ---");
        System.out.println("Informe a altura da pessoa 3: ");
        p3.altura = scan.nextDouble();

        System.out.println("Informe o peso da pessoa 3:");
        p3.peso = scan.nextDouble();

        System.out.println("O IMC da pessoa 3 é: " + p3.calcularImc());
    }

}
