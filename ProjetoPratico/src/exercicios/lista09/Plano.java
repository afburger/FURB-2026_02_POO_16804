package exercicios.lista09;

public abstract class Plano {

    private String nome;
    private int maxDispositivos;

    public Plano(String nome, int maxDispositivos) {
        this.nome = nome;
        this.maxDispositivos = maxDispositivos;
    }

    public String getNome() {
        return nome;
    }

    public int getMaxDispositivos() {
        return maxDispositivos;
    }

    public abstract double calcularMensalidade();

    public abstract boolean temAnuncios();

    public final String resumo() {
        return nome + ": R$ " + calcularMensalidade()
                + " por mes, " + maxDispositivos + " dispositivo(s)";
    }
}
