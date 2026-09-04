package exercicios.lista04;

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
            System.out.println("=== Sonora (Fase 02) ===");
            System.out.println("1 - Cadastrar musica manualmente");
            System.out.println("2 - Cadastrar usuario");
            System.out.println("3 - Definir playlist atual e adicionar musicas");
            System.out.println("4 - Buscar musica por id");
            System.out.println("5 - Buscar musica por titulo");
            System.out.println("6 - Reproduzir uma musica (por id)");
            System.out.println("7 - Listar acervo");
            System.out.println("8 - Gerenciar playlist atual");
            System.out.println("9 - Rodar demonstracao automatica (roteiro Fase 02)");
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
                case 8 -> gerenciarPlaylist(sc, playlistAtual);
                case 9 -> demonstracao();
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
        // O construtor agora lanca IllegalArgumentException se os dados nao fazem
        // sentido; a chamada precisa estar protegida para o menu nao morrer.
        try {
            Musica musica = new Musica(titulo, artista, duracao);
            boolean ok = plataforma.cadastrarMusica(musica);
            if (ok) {
                System.out.println("Cadastrada com id " + musica.getId() + ".");
            } else {
                System.out.println("Nao foi possivel cadastrar (acervo cheio).");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nao foi possivel cadastrar: " + e.getMessage());
        } finally {
            System.out.println("--- operacao de cadastro finalizada ---");
        }
    }

    private static void cadastrarUsuario(Scanner sc, Plataforma plataforma) {
        String nome = lerTexto(sc, "Nome: ");
        String email = lerTexto(sc, "E-mail: ");
        try {
            Usuario usuario = new Usuario(nome, email);
            boolean ok = plataforma.cadastrarUsuario(usuario);
            if (ok) {
                System.out.println("Usuario cadastrado com id " + usuario.getId() + ".");
            } else {
                System.out.println("Nao foi possivel cadastrar (limite de usuarios).");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nao foi possivel cadastrar: " + e.getMessage());
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

        Playlist playlist;
        try {
            playlist = new Playlist(nome, dono);
        } catch (IllegalArgumentException e) {
            System.out.println("Nao foi possivel criar a playlist: " + e.getMessage());
            return null;
        }
        System.out.println("Playlist \"" + nome + "\" criada para " + dono.getNome() + ".");

        int id = lerInt(sc, "Id da musica para adicionar (0 para encerrar): ");
        while (id != 0) {
            Musica musica = plataforma.buscarMusicaPorId(id);
            if (musica == null) {
                // Buscar e nao encontrar continua sendo retorno (fluxo normal).
                System.out.println("Nao existe musica com id " + id + ".");
            } else if (playlist.adicionar(musica)) {
                System.out.println("Adicionada: " + musica.getTitulo());
            } else {
                // Playlist cheia continua sendo retorno false (fluxo normal).
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
            removerPorPosicao(sc, playlist);
        } else if (escolha.equalsIgnoreCase("b")) {
            playlist.reproduzirTudo();
            System.out.println("Todas as musicas da playlist foram reproduzidas mais uma vez.");
        }
    }

    /**
     * Ponto com multiplos catch: a posicao e lida como texto e convertida com
     * parseInt (pode lancar NumberFormatException) e depois usada em
     * removerNaPosicao (pode lancar IndexOutOfBoundsException). Os catch sao
     * testados na ordem, do mais especifico para o mais generico. Um finally
     * fecha a operacao mostrando que ela roda sempre, com erro ou nao.
     */
    private static void removerPorPosicao(Scanner sc, Playlist playlist) {
        System.out.print("Posicao a remover: ");
        String entrada = sc.nextLine();
        try {
            int pos = Integer.parseInt(entrada.trim());
            playlist.removerNaPosicao(pos);
            System.out.println("Removida. Agora ha " + playlist.getQuantidade() + " musica(s).");
        } catch (NumberFormatException e) {
            System.out.println("A posicao precisa ser um numero.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Essa posicao nao existe na playlist: " + e.getMessage());
        } finally {
            System.out.println("--- operacao finalizada ---");
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

    /**
     * Fase 02: le a entrada como texto e converte com Integer.parseInt dentro de
     * um try. Se vier "abc" onde era para vir numero, o parseInt lanca
     * NumberFormatException, o catch avisa e o laco pede de novo, sem derrubar o
     * programa. Substitui o hasNextInt() paliativo da Fase 01.
     */
    private static int lerInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero.");
            }
        }
    }

    private static String lerTexto(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // ------------------------------------------------------- demonstracao (demo)

    /** Executa o roteiro da Fase 02 e imprime os resultados, sem interacao. */
    private static void demonstracao() {
        System.out.println("=== Demonstracao automatica (roteiro da Fase 02) ===");
        Plataforma plataforma = new Plataforma();

        System.out.println("\n[1] Cadastrar musica com titulo vazio (espera IllegalArgumentException tratada):");
        try {
            Musica m = new Musica("", "X", 100);
            plataforma.cadastrarMusica(m);
            System.out.println("    Cadastrada (nao deveria chegar aqui).");
        } catch (IllegalArgumentException e) {
            System.out.println("    Tratada: " + e.getMessage());
        }

        System.out.println("\n[2] Cadastrar musica com duracao negativa (espera IllegalArgumentException tratada):");
        try {
            Musica m = new Musica("Y", "Z", -5);
            plataforma.cadastrarMusica(m);
            System.out.println("    Cadastrada (nao deveria chegar aqui).");
        } catch (IllegalArgumentException e) {
            System.out.println("    Tratada: " + e.getMessage());
        }

        System.out.println("\n[3] Cadastrar usuario com e-mail sem @ (espera IllegalArgumentException tratada):");
        try {
            Usuario u = new Usuario("Fulano", "fulano.sonora.dev");
            plataforma.cadastrarUsuario(u);
            System.out.println("    Cadastrado (nao deveria chegar aqui).");
        } catch (IllegalArgumentException e) {
            System.out.println("    Tratada: " + e.getMessage());
        }

        // Monta um usuario e uma playlist validos para os proximos passos.
        Usuario dono = new Usuario("Andre", "andre@sonora.dev");
        Musica m1 = new Musica("Bohemian Rhapsody", "Queen", 354);
        Musica m2 = new Musica("Numb", "Linkin Park", 185);
        Musica m3 = new Musica("Clocks", "Coldplay", 307);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        plataforma.cadastrarMusica(m3);
        Playlist favoritas = new Playlist("Favoritas", dono);
        favoritas.adicionar(m1);
        favoritas.adicionar(m2);
        favoritas.adicionar(m3);

        System.out.println("\n[4] getNaPosicao(50) numa playlist de 3 (espera IndexOutOfBoundsException, nao null):");
        try {
            Musica m = favoritas.getNaPosicao(50);
            System.out.println("    Retornou: " + m);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Tratada: " + e.getMessage());
        }

        System.out.println("\n[5] Encher a playlist ate 100 e tentar a 101a (espera adicionar devolver false):");
        Playlist cheia = new Playlist("Cheia", dono);
        for (int i = 0; i < 100; i++) {
            cheia.adicionar(new Musica("Faixa " + i, "Artista", 120));
        }
        boolean coube = cheia.adicionar(new Musica("Sobra", "Artista", 120));
        System.out.println("    quantidade: " + cheia.getQuantidade() + " | adicionar 101a retornou: " + coube);

        System.out.println("\n[6] Buscar por id inexistente (espera null - continua sendo retorno):");
        Musica naoAchada = plataforma.buscarMusicaPorId(9999);
        System.out.println("    id 9999 -> " + (naoAchada == null ? "null" : naoAchada.getTitulo()));

        System.out.println("\n[7] Menu convertendo texto onde se espera numero (parseInt trata sem cair):");
        try {
            int valor = Integer.parseInt("abc");
            System.out.println("    Convertido: " + valor);
        } catch (NumberFormatException e) {
            System.out.println("    Tratada: entrada nao numerica, o menu avisaria e pediria de novo.");
        }

        System.out.println("\n[8] Bloco com multiplos catch, provocando duas excecoes diferentes:");
        System.out.println("    a) posicao \"abc\":");
        reproduzirPorPosicao(favoritas, "abc");
        System.out.println("    b) posicao \"50\":");
        reproduzirPorPosicao(favoritas, "50");

        System.out.println("\n[9] finally rodando no sucesso e no erro:");
        System.out.println("    caso de sucesso (posicao 0):");
        reproduzirPorPosicao(favoritas, "0");
        System.out.println("    caso de erro (posicao 99):");
        reproduzirPorPosicao(favoritas, "99");

        System.out.println("\n=== Fim da demonstracao ===");
    }

    /**
     * Auxiliar da demonstracao: usa o mesmo padrao de multiplos catch + finally.
     * parseInt pode lancar NumberFormatException; getNaPosicao pode lancar
     * IndexOutOfBoundsException. O catch mais especifico vem primeiro.
     */
    private static void reproduzirPorPosicao(Playlist playlist, String textoPosicao) {
        try {
            int pos = Integer.parseInt(textoPosicao.trim());
            Musica m = playlist.getNaPosicao(pos);
            m.reproduzir();
            System.out.println("        Tocando: " + m.getTitulo());
        } catch (NumberFormatException e) {
            System.out.println("        A posicao precisa ser um numero.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("        Essa posicao nao existe na playlist.");
        } finally {
            System.out.println("        --- operacao finalizada ---");
        }
    }
}
