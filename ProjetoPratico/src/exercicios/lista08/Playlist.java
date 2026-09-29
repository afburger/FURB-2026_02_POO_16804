package exercicios.lista08;

import java.util.ArrayList;

public class Playlist {

    private String nome;
    private Usuario dono;
    private ArrayList<Musica> musicas;

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
        musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

    public int getQuantidade() {
        return musicas.size();
    }

    /**
     * Passar uma musica null e uso indevido: lanca IllegalArgumentException.
     * Playlist cheia e um estado normal e previsivel: continua devolvendo false.
     */
    public boolean adicionar(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma música nula à playlist.");
        }
        return musicas.add(musica);
    }

    /**
     * Uso indevido: pedir uma posicao fora do intervalo valido agora lanca
     * IndexOutOfBoundsException em vez de devolver null.
     */
    public Musica getNaPosicao(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            throw new IndexOutOfBoundsException(
                    "Posição inválida: " + indice + ". A playlist tem " + musicas.size() + " música(s).");
        }
        return musicas.get(indice);
    }

    /**
     * Uso indevido: indice fora do intervalo valido agora lanca
     * IndexOutOfBoundsException em vez de devolver false.
     */
    public void removerNaPosicao(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            throw new IndexOutOfBoundsException(
                    "Posição inválida: " + indice + ". A playlist tem " + musicas.size() + " música(s).");
        }
        
        musicas.remove(indice);
    }

    public int getDuracaoTotalSegundos() {
        int total = 0;
        for (Musica musica : musicas) {
            total = total + musica.getDuracaoEmSegundos();
        }
        return total;
    }

    public void reproduzirTudo() {
        for (Musica musica : musicas) {
            musica.reproduzir();
        }
    }

    public String getDuracaoFormatada() {
        int totalEmSegundos = getDuracaoTotalSegundos();
        int minutos = totalEmSegundos / 60;
        int segundos = totalEmSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
