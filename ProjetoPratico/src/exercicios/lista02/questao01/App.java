package exercicios.lista02.questao01;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Pessoa[] pessoas = new Pessoa[3];

        for (int i = 0; i < pessoas.length; i++) {
            System.out.println("Informe o nome da pessoa:");
            String nome = teclado.next();

            System.out.println("Informe a altura da pessoa:");
            double altura = teclado.nextDouble();

            System.out.println("Informe o peso da pessoa:");
            double peso = teclado.nextDouble();

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setAltura(altura);
            pessoa.setPeso(peso);

            pessoas[i] = pessoa;
        }

        for (int i = pessoas.length - 1; i >= 0; i--) {
            Pessoa pessoaLeitura = pessoas[i];
            System.out.println("Nome: " + pessoaLeitura.getNome());
            System.out.println("Altura: " + pessoaLeitura.getAltura());
            System.out.println("Peso: " + pessoaLeitura.getPeso());
            System.out.println("IMC: " + pessoaLeitura.calcularImc());

            System.out.println();
            System.out.println("-----------------");
            System.out.println();



        }
    }
}
