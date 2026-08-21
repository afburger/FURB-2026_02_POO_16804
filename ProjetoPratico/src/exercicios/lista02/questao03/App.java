package exercicios.lista02.questao03;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ContaBancaria conta1 = new ContaBancaria();
        System.out.println("Informe o titular da primeira conta:");
        conta1.setTitular(scan.nextLine());

        System.out.println("Informe o número da primeira conta:");
        conta1.setNumero(scan.nextLine());

        ContaBancaria conta2 = new ContaBancaria();
        System.out.println("Informe o titular da segunda conta:");
        conta2.setTitular(scan.nextLine());

        System.out.println("Informe o número da segunda conta");
        conta2.setNumero(scan.nextLine());

        conta1.depositar(1000);
        conta1.depositar(700);

        conta2.depositar(5000);
        conta2.sacar(3000);

        conta2.transferir(conta1, 1800);

        System.out.println("Conta 1 - Titular: " + conta1.getTitular() + " saldo = " + conta1.getSaldo());
        System.out.println("Conta 2 - Titular: " + conta2.getTitular() + " saldo = " + conta2.getSaldo());

    }
}
