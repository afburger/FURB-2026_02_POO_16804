package exercicios.lista03;

public class Playlist {

    private Usuario dono;
    private String nome;
    private Musica[] musicas;
    private int quantidade;

    public Playlist(String nome, Usuario dono) {
        this.nome =  nome;
        this.dono = dono;
        musicas = new Musica[100];
    }

    public Usuario getDono() {
        return dono;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean adicionar(Musica musica) {
        if (musica == null || musicas.length == quantidade) {
            return false;
        } else {
            musicas[quantidade] = musica;
            quantidade++;
            return true;
        }
    }

    public Musica getNaPosicao(int indice) {
        if (indice >= 0 && indice < quantidade) {
            return musicas[indice];
        }
        return null;
    }

    public boolean removerNaPosicao(int indice) {
        if (indice >= 0 && indice < quantidade) {
            // 0    1   2   3   4   5
            // [A] [B] [C] [D] [F] [G]
            // [A] [B] [ ] [D] [F] [G]
            // [A] [B] [D] [F] [G] [null]
            for (int i = indice; i < quantidade; i++) {
                musicas[i] = musicas[i + 1];
            }
            musicas[quantidade] = null;
            quantidade--;
            return true;
        }
        return false;
    }

    public int getDuracaoTotalSegundos() {
        int soma = 0;
        for (int i = 0; i < quantidade; i++) {
            Musica musica = musicas[i];
            soma = soma + musica.getDuracaoSegundos();
        }
        return soma;
    }

    public void reproduzirTudo() {
        for (int i = 0; i < quantidade; i++) {
            musicas[i].reproduzir();
        }
    }

    public String getDuracaoTotalFormarada() {
        int duracaoSegundos = getDuracaoTotalSegundos();
        return Utils.getDuracaoFormatada(duracaoSegundos);
    }
}
