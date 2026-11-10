package exemplos.unidade06;

public class ContaBancaria {

    private String codigo;
    private String nome;
    private int saldo;

    public ContaBancaria(String codigo, String nome, int saldo) {
        this.codigo = codigo;
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getSaldo() {
        return saldo;
    }


}
