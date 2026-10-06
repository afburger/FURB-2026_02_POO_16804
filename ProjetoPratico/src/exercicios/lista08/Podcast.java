package exercicios.lista08;

public class Podcast extends Conteudo {

    private String apresentador;
    private int numeroEpisodio;

    public Podcast(String titulo, String apresentador, int numeroEpisodio, int duracaoEmSegundos) {
        super(titulo, duracaoEmSegundos);
        setApresentador(apresentador);
        setNumeroEpisodio(numeroEpisodio);
    }

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        this.apresentador = apresentador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        if (numeroEpisodio < 1) {
            throw new IllegalArgumentException("Número do episódio deve ser maior ou igual a 1.");
        }
        this.numeroEpisodio = numeroEpisodio;
    }

    @Override
    public String toString() {
        return super.toString() + " - Ep. " + numeroEpisodio + " (" + apresentador + ")";
    }

}
