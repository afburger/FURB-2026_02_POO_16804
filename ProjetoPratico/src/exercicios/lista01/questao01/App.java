package exercicios.lista01.questao01;

public class App {

    public static void main(String[] args) {
        // Declara a variável de referência
        Pessoa pessoa;
        // Criar o objeto ou Insanciar o objeto
        pessoa = new Pessoa();

        pessoa.altura = 1.75;
        pessoa.peso = 78;

        System.out.println("O IMC da pessoa é: " + pessoa.calcularImc());
    }

}
