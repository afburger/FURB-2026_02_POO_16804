package exemplos.unidade01.membroClasse;

public class App {

    public static void main(String[] args) {
        ContaBancaria cb = new ContaBancaria();
        
        cb.setTitular("André");
        cb.setNumeroConta(123);
        cb.depositar(1000);
        cb.sacar(100);

        ContaBancaria c1 = new ContaBancaria();
        ContaBancaria c2 = new ContaBancaria();
        ContaBancaria c3 = new ContaBancaria();

        System.out.println("O saldo atual é: " + cb.getSaldo());

        cb.sacar(1200);
        
        System.out.println("O saldo agora é de: "+ cb.getSaldo());

        System.out.println("A quantidade de contas nesse banco é de:" + c1.getQtdContas());

    }

}
