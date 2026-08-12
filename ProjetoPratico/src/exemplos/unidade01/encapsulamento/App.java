package exemplos.unidade01.encapsulamento;

public class App {

    public static void main(String[] args) {
        ContaBancaria cb = new ContaBancaria();

        cb.setTitular("André");
        cb.setNumeroConta(123);
        cb.depositar(1000);
        cb.sacar(100);

        System.out.println("O saldo atual é: " + cb.getSaldo());

        cb.sacar(1200);
        
        System.out.println("O saldo agora é de: "+ cb.getSaldo());
    }

}
