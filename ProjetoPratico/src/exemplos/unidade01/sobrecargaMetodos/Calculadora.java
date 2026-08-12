package exemplos.unidade01.sobrecargaMetodos;

public class Calculadora {

    public static void somar(int valor1, int valor2) {
        int soma = valor1 + valor2;
        System.out.println("O resultado da soma é: " + soma);
    }

    public static void somar(int valor1, int valor2, int valor3) {
        int soma = valor1 + valor2 + valor3;
        System.out.println("O resultado da soma é: " + soma);
    }

}
