package exercicios.lista03;

public class Musica {

    private static int contador = 1;

    private int id;
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private int reproducoes;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        id = contador;
        contador++;
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public void reproduzir() {
        reproducoes++;
    }

    public String getDuracaoFormatada() {
        return Utils.getDuracaoFormatada(duracaoSegundos);
    }
}
