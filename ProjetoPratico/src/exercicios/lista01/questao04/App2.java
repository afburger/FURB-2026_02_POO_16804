package exercicios.lista01.questao04;

import java.util.Scanner;

public class App2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pessoa[] pessoas = new Pessoa[2];

        for (int i = 0; i < pessoas.length; i++) {
            Pessoa pessoa = new Pessoa();
            int numeroPessoa = i+1;
            System.out.println("Informe o nome da pessoa " + numeroPessoa);
            pessoa.nome = scan.next();

            System.out.println("Informe a altura da pessoa " + numeroPessoa);
            pessoa.altura = scan.nextDouble();

            System.out.println("Informe o peso da pessoa " + numeroPessoa);
            pessoa.peso = scan.nextDouble();

            pessoas[i] = pessoa;
        }

        for (int i = pessoas.length - 1; i >= 0; i--) {
            int numeroPessoa = i+1;
            System.out.println("--- Pessoa " + numeroPessoa + " ---");
            pessoas[i].exibirInformacoes();
        }

    }

}
