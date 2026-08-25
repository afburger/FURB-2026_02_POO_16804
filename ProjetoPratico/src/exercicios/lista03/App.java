package exercicios.lista03;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Plataforma plataforma = new Plataforma();
        popularExemplos(plataforma);
        Playlist playlistAtual = null;

        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("=== Sonora ===");
            System.out.println("1 - Cadastrar musica manualmente");
            System.out.println("2 - Cadastrar usuario");
            System.out.println("3 - Criar playlist e adicionar musicas");
            System.out.println("4 - Buscar musica por id");
            System.out.println("5 - Buscar musica por titulo");
            System.out.println("6 - Reproduzir uma musica (por id)");
            System.out.println("7 - Listar acervo");
            System.out.println("0 - Sair");
            opcao = lerInt(sc, "Opcao: ");

            switch (opcao) {
                case 1 -> cadastrarMusica(sc, plataforma);
                case 2 -> cadastrarUsuario(sc, plataforma);
                case 3 -> playlistAtual = definirPlaylist(sc, plataforma);
                case 4 -> buscarPorId(sc, plataforma);
                case 5 -> buscarPorTitulo(sc, plataforma);
                case 6 -> reproduzirUma(sc, plataforma);
                case 7 -> listarAcervo(plataforma);
                case 0 -> System.out.println("Ate mais!");
                default -> System.out.println("Opcao inexistente.");
            }
        }
        sc.close();
    }

    // ------------------------------------------------------------------ acoes

    private static void cadastrarMusica(Scanner sc, Plataforma plataforma) {
        String titulo = lerTexto(sc, "Titulo: ");
        String artista = lerTexto(sc, "Artista: ");
        int duracao = lerInt(sc, "Duracao (segundos): ");
        Musica musica = new Musica(titulo, artista, duracao);
        boolean ok = plataforma.cadastrarMusica(musica);
        if (ok) {
            System.out.println("Cadastrada com id " + musica.getId() + ".");
        } else {
            System.out.println("Nao foi possivel cadastrar (acervo cheio).");
        }
    }

    private static void cadastrarUsuario(Scanner sc, Plataforma plataforma) {
        String nome = lerTexto(sc, "Nome: ");
        String email = lerTexto(sc, "E-mail: ");
        Usuario usuario = new Usuario(nome, email);
        boolean ok = plataforma.cadastrarUsuario(usuario);
        if (ok) {
            System.out.println("Usuario cadastrado com id " + usuario.getId() + ".");
        } else {
            System.out.println("Nao foi possivel cadastrar (limite de usuarios).");
        }
    }

    private static Playlist definirPlaylist(Scanner sc, Plataforma plataforma) {
        if (plataforma.getTotalUsuarios() == 0) {
            System.out.println("Cadastre um usuario antes de criar uma playlist.");
            return null;
        }
        // Simplificacao de referencia: usa o primeiro usuario como dono.
        Usuario dono = plataforma.getUsuarioNoIndice(0);
        String nome = lerTexto(sc, "Nome da playlist: ");
        Playlist playlist = new Playlist(nome, dono);
        System.out.println("Playlist \"" + nome + "\" criada para " + dono.getNome() + ".");

        int id = lerInt(sc, "Id da musica para adicionar (0 para encerrar): ");
        while (id != 0) {
            Musica musica = plataforma.buscarMusicaPorId(id);
            if (musica == null) {
                System.out.println("Nao existe musica com id " + id + ".");
            } else if (playlist.adicionar(musica)) {
                System.out.println("Adicionada: " + musica.getTitulo());
            } else {
                System.out.println("Nao foi possivel adicionar (playlist cheia).");
            }
            id = lerInt(sc, "Id da musica para adicionar (0 para encerrar): ");
        }
        System.out.println("Playlist com " + playlist.getQuantidade() + " musica(s), duracao total "
                + formatar(playlist.getDuracaoTotalSegundos()) + ".");
        return playlist;
    }

    private static void buscarPorId(Scanner sc, Plataforma plataforma) {
        int id = lerInt(sc, "Id: ");
        Musica musica = plataforma.buscarMusicaPorId(id);
        System.out.println(musica == null ? "Nenhuma musica com esse id." : descrever(musica));
    }

    private static void buscarPorTitulo(Scanner sc, Plataforma plataforma) {
        String titulo = lerTexto(sc, "Titulo: ");
        Musica musica = plataforma.buscarMusica(titulo);
        System.out.println(musica == null ? "Nenhuma musica com esse titulo." : descrever(musica));
    }

    private static void reproduzirUma(Scanner sc, Plataforma plataforma) {
        int id = lerInt(sc, "Id da musica: ");
        Musica musica = plataforma.buscarMusicaPorId(id);
        if (musica == null) {
            System.out.println("Nenhuma musica com esse id.");
            return;
        }
        musica.reproduzir();
        System.out.println("Tocando " + musica.getTitulo() + " (reproducoes: " + musica.getReproducoes() + ").");
    }

    private static void listarAcervo(Plataforma plataforma) {
        if (plataforma.getTotalMusicas() == 0) {
            System.out.println("Acervo vazio.");
            return;
        }
        System.out.println("--- Acervo (" + plataforma.getTotalMusicas() + ") ---");
        for (int i = 0; i < plataforma.getTotalMusicas(); i++) {
            System.out.println(descrever(plataforma.getMusicaNoIndice(i)));
        }
    }

    private static void gerenciarPlaylist(Scanner sc, Playlist playlist) {
        if (playlist == null) {
            System.out.println("Nenhuma playlist atual. Use a opcao 3 primeiro.");
            return;
        }
        System.out.println("Playlist \"" + playlist.getNome() + "\" (" + playlist.getQuantidade() + " musicas):");
        for (int i = 0; i < playlist.getQuantidade(); i++) {
            System.out.println("  [" + i + "] " + playlist.getNaPosicao(i).getTitulo());
        }
        System.out.println("a - remover por posicao | b - reproduzir tudo | qualquer outra - voltar");
        String escolha = lerTexto(sc, "Escolha: ");
        if (escolha.equalsIgnoreCase("a")) {
            int pos = lerInt(sc, "Posicao a remover: ");
            System.out.println(playlist.removerNaPosicao(pos)
                    ? "Removida. Agora ha " + playlist.getQuantidade() + " musica(s)."
                    : "Posicao invalida.");
        } else if (escolha.equalsIgnoreCase("b")) {
            playlist.reproduzirTudo();
            System.out.println("Todas as musicas da playlist foram reproduzidas mais uma vez.");
        }
    }

    // ------------------------------------------------------------- utilitarios

    private static void popularExemplos(Plataforma plataforma) {
        plataforma.cadastrarMusica(new Musica("Bohemian Rhapsody", "Queen", 354));
        plataforma.cadastrarMusica(new Musica("Numb", "Linkin Park", 185));
        plataforma.cadastrarMusica(new Musica("Clocks", "Coldplay", 307));
        plataforma.cadastrarUsuario(new Usuario("Andre", "andre@sonora.dev"));
    }

    private static String descrever(Musica m) {
        return String.format("#%d  %s - %s  [%s]  reproducoes: %d",
                m.getId(), m.getTitulo(), m.getArtista(), m.getDuracaoFormatada(), m.getReproducoes());
    }

    private static String formatar(int totalSegundos) {
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    private static int lerInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            sc.next(); // descarta o token invalido
            System.out.print("Digite um numero. " + prompt);
        }
        int valor = sc.nextInt();
        sc.nextLine(); // consome o restante da linha
        return valor;
    }

    private static String lerTexto(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // ------------------------------------------------------- demonstracao (demo)

    /** Executa o roteiro da atividade e imprime os resultados, sem interacao. */
    private static void demonstracao() {
        System.out.println("=== Demonstracao automatica (roteiro da Fase 01) ===");
        Plataforma plataforma = new Plataforma();

        System.out.println("\n[1] Cadastrar tres musicas e conferir ids sequenciais:");
        Musica m1 = new Musica("Bohemian Rhapsody", "Queen", 354);
        Musica m2 = new Musica("Numb", "Linkin Park", 185);
        Musica m3 = new Musica("Ten Minutes", "Teste", 600);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        plataforma.cadastrarMusica(m3);
        System.out.println("    ids: " + m1.getId() + ", " + m2.getId() + ", " + m3.getId());

        System.out.println("\n[2] Reproduzir a primeira musica tres vezes:");
        m1.reproduzir();
        m1.reproduzir();
        m1.reproduzir();
        System.out.println("    reproducoes de \"" + m1.getTitulo() + "\": " + m1.getReproducoes());

        System.out.println("\n[3] Duracao formatada de 354, 65 e 600 segundos:");
        System.out.println("    354 -> " + new Musica("x", "y", 354).getDuracaoFormatada());
        System.out.println("    65  -> " + new Musica("x", "y", 65).getDuracaoFormatada());
        System.out.println("    600 -> " + new Musica("x", "y", 600).getDuracaoFormatada());

        System.out.println("\n[4] Criar usuario, playlist e somar duracao:");
        Usuario u1 = new Usuario("Andre", "andre@sonora.dev");
        plataforma.cadastrarUsuario(u1);
        Playlist favoritas = new Playlist("Favoritas", u1);
        favoritas.adicionar(m1);
        favoritas.adicionar(m2);
        favoritas.adicionar(m3);
        System.out.println("    quantidade: " + favoritas.getQuantidade()
                + " | duracao total: " + formatar(favoritas.getDuracaoTotalSegundos()));

        System.out.println("\n[5] Encher uma playlist ate 100 e tentar a 101a:");
        Playlist cheia = new Playlist("Cheia", u1);
        for (int i = 0; i < 100; i++) {
            cheia.adicionar(new Musica("Faixa " + i, "Artista", 120));
        }
        boolean coube = cheia.adicionar(new Musica("Sobra", "Artista", 120));
        System.out.println("    quantidade: " + cheia.getQuantidade() + " | adicionar 101a retornou: " + coube);

        System.out.println("\n[6] Remover a musica do meio e conferir que nao ha buraco:");
        System.out.println("    antes: [0]=" + favoritas.getNaPosicao(0).getTitulo()
                + " [1]=" + favoritas.getNaPosicao(1).getTitulo()
                + " [2]=" + favoritas.getNaPosicao(2).getTitulo());
        favoritas.removerNaPosicao(1);
        System.out.println("    depois: quantidade=" + favoritas.getQuantidade()
                + " [0]=" + favoritas.getNaPosicao(0).getTitulo()
                + " [1]=" + favoritas.getNaPosicao(1).getTitulo());

        System.out.println("\n[7] Buscar por id existente e inexistente:");
        Musica achada = plataforma.buscarMusicaPorId(m2.getId());
        Musica naoAchada = plataforma.buscarMusicaPorId(9999);
        System.out.println("    id " + m2.getId() + " -> " + (achada == null ? "null" : achada.getTitulo()));
        System.out.println("    id 9999 -> " + (naoAchada == null ? "null" : naoAchada.getTitulo()));

        System.out.println("\n[8] Buscar por titulo (sobrecarga):");
        Musica porTitulo = plataforma.buscarMusica("numb");
        System.out.println("    \"numb\" -> " + (porTitulo == null ? "null" : porTitulo.getTitulo() + " (id " + porTitulo.getId() + ")"));

        System.out.println("\n[9] reproduzirTudo somando uma reproducao em cada faixa da playlist:");
        System.out.println("    antes:  m1=" + m1.getReproducoes() + " m3=" + m3.getReproducoes());
        favoritas.reproduzirTudo();
        System.out.println("    depois: m1=" + m1.getReproducoes() + " m3=" + m3.getReproducoes());

        System.out.println("\n=== Fim da demonstracao ===");
    }
}
