package exemplos.unidade01.lancamentoExcecao;

public class ContaBancaria {

    private String numero;
    private String titular;
    private double saldo;
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        } else {
            throw new IllegalArgumentException("Valor para depósito inválido");
        }
    }

    public void sacar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do saque não pode ser negativo");
        }
        if (saldo < valor) {
            throw new IllegalArgumentException("O saldo é insuficiente");
        }
        saldo = saldo - valor;     
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
         if (valor > 0 && saldo >= valor) {
             this.sacar(valor);
             contaDestino.depositar(valor);
        } else {
            throw new IllegalArgumentException();
        }

    }
}
