package exercicios.lista08;

public class App {

    public static void main(String[] args) {
        // Parte D: demonstra a hierarquia funcionando
        Musica musica1 = null;
        Musica musica2 = null;
        Podcast podcast = null;

        try {
            musica1 = new Musica("Bohemian Rhapsody", "Queen", "A Night at the Opera", 354);
            musica2 = new Musica("Hotel California", "Eagles", "Hotel California", 391);
            podcast = new Podcast("The Joe Rogan Experience", "Joe Rogan", 1, 3600);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar conteudo: " + e.getMessage());
            return;
        }

        // reproduzir() esta na superclasse e e herdado pelas subclasses sem reescrita
        musica1.reproduzir();
        musica2.reproduzir();
        podcast.reproduzir();

        System.out.println();

        // toString() sobrescrito em cada subclasse, acrescentando campos proprios
        System.out.println(musica1);
        System.out.println(musica2);
        System.out.println(podcast);

        // Testa validacao: titulo vazio deve lancar excecao
        try {
            new Musica("", "Artista", "Album", 180);
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidacao funcionando: " + e.getMessage());
        }

        // Testa validacao: episodio invalido
        try {
            new Podcast("Podcast X", "Host", 0, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Validacao funcionando: " + e.getMessage());
        }
    }
}
