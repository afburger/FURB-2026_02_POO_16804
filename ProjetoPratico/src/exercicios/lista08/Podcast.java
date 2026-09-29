package exercicios.lista08;

public class Podcast extends Conteudo {

    private String apresentador;
    private int numeroEpisodio;

    public Podcast(String titulo, int duracaoEmSegundos, String apresentador, int numeroEpisodio) {
        super(titulo, duracaoEmSegundos);
        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException("Número do episódio deve ser maior que zero.");
        }
        this.apresentador = apresentador;
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getApresentador() {
        return apresentador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    @Override
    public String toString() {
        return super.toString() + " " + numeroEpisodio + " - " + apresentador;
    }
}
