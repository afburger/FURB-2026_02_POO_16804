package exemplos.unidade01.membroClasse;

public class ContaBancaria {

    private String titular;
    private int numeroConta;
    private double saldo;
    
    // declara uma variável estática
    private static int qtdContas;

    public ContaBancaria() {
        qtdContas++;
    }

    // declara um método estático.
    public static int getQtdContas() {
        return qtdContas;
    }

    void depositar(double valor) {
        if (valor < 0) {
            System.out.println("Você não pode" +
            "depositar um valor negativo");
        } else {
            saldo = saldo + valor;
        }
    }

    void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente");
        } else {
            saldo = saldo - valor;
        }
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }
    
}
