package exercicios.lista04;

public class Playlist {

    private String nome;
    private Usuario dono;
    private Musica[] musicas;
    private int quantidadeMusicas;

    public Playlist(String nome, Usuario dono) {
        // Fase 02: valida primeiro, atribui depois.
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido. O nome da playlist não pode ser vazio.");
        }
        if (dono == null) {
            throw new IllegalArgumentException("A playlist precisa de um dono. O dono não pode ser nulo.");
        }
        this.nome = nome;
        this.dono = dono;
        musicas = new Musica[100];
    }

    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

    public int getQuantidade() {
        return quantidadeMusicas;
    }

    /**
     * Passar uma musica null e uso indevido: lanca IllegalArgumentException.
     * Playlist cheia e um estado normal e previsivel: continua devolvendo false.
     */
    public boolean adicionar(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma música nula à playlist.");
        }
        if (quantidadeMusicas == musicas.length) {
            return false;
        }
        musicas[quantidadeMusicas] = musica;
        quantidadeMusicas++;
        return true;
    }

    /**
     * Uso indevido: pedir uma posicao fora do intervalo valido agora lanca
     * IndexOutOfBoundsException em vez de devolver null.
     */
    public Musica getNaPosicao(int indice) {
        if (indice < 0 || indice >= quantidadeMusicas) {
            throw new IndexOutOfBoundsException(
                    "Posição inválida: " + indice + ". A playlist tem " + quantidadeMusicas + " música(s).");
        }
        return musicas[indice];
    }

    /**
     * Uso indevido: indice fora do intervalo valido agora lanca
     * IndexOutOfBoundsException em vez de devolver false.
     */
    public void removerNaPosicao(int indice) {
        if (indice < 0 || indice >= quantidadeMusicas) {
            throw new IndexOutOfBoundsException(
                    "Posição inválida: " + indice + ". A playlist tem " + quantidadeMusicas + " música(s).");
        }
        //  0  1  2  3  4
        // [A][X][Y][J][K]
        // [A][X][J][K][ ]
        for (int i = indice; i < quantidadeMusicas - 1; i++) {
            musicas[i] = musicas[i + 1];
        }
        musicas[quantidadeMusicas - 1] = null;
        quantidadeMusicas--;
    }

    public int getDuracaoTotalSegundos() {
        int total = 0;
        for (int i = 0; i < quantidadeMusicas; i++) {
            Musica musicaPosicao = musicas[i];
            total = total + musicaPosicao.getDuracaoEmSegundos();
        }
        return total;
    }

    public void reproduzirTudo() {
        for (int i = 0; i < quantidadeMusicas; i++) {
            musicas[i].reproduzir();
        }
    }

    public String getDuracaoFormatada() {
        int totalEmSegundos = getDuracaoTotalSegundos();
        int minutos = totalEmSegundos / 60;
        int segundos = totalEmSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
