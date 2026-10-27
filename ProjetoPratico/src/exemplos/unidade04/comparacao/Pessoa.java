package exemplos.unidade04.comparacao;

public class Pessoa {

    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pessoa) {
            Pessoa outra = (Pessoa) obj;
            return nome.equals(outra.getNome()) 
                && cpf.equals(outra.getCpf());
        }
        return super.equals(obj);
    }

}
