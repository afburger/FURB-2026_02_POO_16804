package exercicios.lista01.questao04;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        Pessoa p3 = new Pessoa();

        System.out.println("--- Pessoa 1 ---");
        System.out.println("Informe o nome da pessoa 1:");
        p1.nome = scan.next();

        System.out.println("Informe a altura da pessoa 1: ");
        p1.altura = scan.nextDouble();

        System.out.println("Informe o peso da pessoa 1:");
        p1.peso = scan.nextDouble();      

        System.out.println("--- Pessoa 2 ---");
        System.out.println("Informe o nome da pessoa 2:");
        p2.nome = scan.next();

        System.out.println("Informe a altura da pessoa 2: ");
        p2.altura = scan.nextDouble();

        System.out.println("Informe o peso da pessoa 2:");
        p2.peso = scan.nextDouble();

        System.out.println("--- Pessoa 3 ---");
        System.out.println("Informe o nome da pessoa 3:");
        p3.nome = scan.next();

        System.out.println("Informe a altura da pessoa 3: ");
        p3.altura = scan.nextDouble();

        System.out.println("Informe o peso da pessoa 3:");
        p3.peso = scan.nextDouble();

        System.out.println("--- Pessoa 3 ---");
        p3.exibirInformacoes();
        System.out.println("--- Pessoa 2 ---");
        p2.exibirInformacoes();
        System.out.println("--- Pessoa 1 ---");
        p1.exibirInformacoes();
    }

}
