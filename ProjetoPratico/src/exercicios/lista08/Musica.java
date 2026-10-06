package exercicios.lista08;

public class Musica extends Conteudo {

    private String artista;
    private String album;

    public Musica(String titulo, String artista, String album, int duracaoEmSegundos) {
        super(titulo, duracaoEmSegundos);
        setArtista(artista);
        setAlbum(album);
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido. O artista não pode ser vazio.");
        }
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + artista + " (" + album + ")";
    }

}
