package exercicios.lista04;

public class Musica {

    private static int contador = 1;

    private int id;
    private String titulo;
    private String artista;
    private int duracaoEmSegundos;
    private int reproducoes;

    public Musica(String titulo, String artista, int duracaoEmSegundos) {
        // Fase 02: um objeto nao pode nascer em estado invalido.
        // Regra do setSalario visto em aula: valida primeiro, lanca se estiver
        // errado, e so entao atribui.
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido. O título não pode ser vazio.");
        }
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido. O artista não pode ser vazio.");
        }
        if (duracaoEmSegundos <= 0) {
            throw new IllegalArgumentException(
                    "Duração inválida: " + duracaoEmSegundos + ". A duração deve ser maior que zero.");
        }

        this.titulo = titulo;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos;
        // So consome um id depois de validar: se o construtor lanca, nenhum id e gasto.
        this.id = contador;
        contador++;
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

    public int getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void reproduzir() {
        reproducoes++;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public String getDuracaoFormatada() {
        int minutos = duracaoEmSegundos / 60;
        int segundos = duracaoEmSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

}
