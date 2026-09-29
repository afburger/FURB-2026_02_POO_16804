package exercicios.lista08;

public class Musica extends Conteudo {

    private String artista;
    private String album;
    private int reproducoes;

    public Musica(String titulo, int duracaoEmSegundos, String artista, String album) {
        super(titulo, duracaoEmSegundos);
        this.artista = artista;
        this.album = album;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    @Override
    public void reproduzir() {
        reproducoes++;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    @Override
    public String toString() {
        return super.toString() + " " + album + " - " + artista;
    }
}
