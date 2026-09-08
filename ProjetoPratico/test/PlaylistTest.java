

import org.junit.jupiter.api.Test;

import exercicios.lista04.Musica;
import exercicios.lista04.Playlist;
import exercicios.lista04.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    private Usuario dono;
    private Musica a;
    private Musica b;
    private Musica c;
    private Playlist playlist;

    @BeforeEach
    void montarCenario() {
        dono = new Usuario("Ana", "ana@email.com");
        a = new Musica("Faixa A", "Artista 1", 200);
        b = new Musica("Faixa B", "Artista 2", 180);
        c = new Musica("Faixa C", "Artista 3", 240);
        playlist = new Playlist("Favoritas", dono);
    }

    // ----- PL03: adicionar -----

    @Test
    @DisplayName("Adicionar música em playlist com espaço retorna true")
    void adicionarComEspaco() {
        assertTrue(playlist.adicionar(a));
        assertEquals(1, playlist.getQuantidade());
    }

    @Test
    @DisplayName("Adicionar várias músicas dentro da capacidade")
    void adicionarVarias() {
        assertTrue(playlist.adicionar(a));
        assertTrue(playlist.adicionar(b));
        assertTrue(playlist.adicionar(c));
        assertEquals(3, playlist.getQuantidade());
    }

    @Test
    @DisplayName("Adicionar além da capacidade retorna false")
    void adicionarAlemDaCapacidade() {
        for (int i = 0; i < 100; i++) {
            assertTrue(playlist.adicionar(new Musica("Faixa " + i, "Artista", 100)));
        }
        assertFalse(playlist.adicionar(new Musica("Excedente", "Artista", 100)));
        assertEquals(100, playlist.getQuantidade());
    }

    // ----- PL04: getNaPosicao -----

    @Test
    @DisplayName("Posição válida devolve a música correta")
    void getNaPosicaoValida() {
        playlist.adicionar(a);
        playlist.adicionar(b);
        playlist.adicionar(c);
        assertEquals(b, playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("Índice negativo lança exceção")
    void getNaPosicaoNegativa() {
        playlist.adicionar(a);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.getNaPosicao(-1));
    }

    @Test
    @DisplayName("Índice além da quantidade lança exceção")
    void getNaPosicaoAcimaDaQuantidade() {
        playlist.adicionar(a); // Índice = 0
        playlist.adicionar(b); // Índice = 1
        playlist.adicionar(c); // Índice = 2
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.getNaPosicao(3));
    }

    // ----- PL05: removerNaPosicao -----

    @Test
    @DisplayName("Remover reorganiza sem deixar buraco")
    void removerReorganiza() {
        playlist.adicionar(a);
        playlist.adicionar(b);
        playlist.adicionar(c);
        playlist.removerNaPosicao(0);
        assertEquals(2, playlist.getQuantidade());
        assertEquals(b, playlist.getNaPosicao(0));
        assertEquals(c, playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("Remover em índice negativo lança exceção")
    void removerIndiceNegativo() {
        playlist.adicionar(a);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.removerNaPosicao(-1));
    }

    @Test
    @DisplayName("Remover em índice além da quantidade lança exceção")
    void removerIndiceAlem() {
        playlist.adicionar(a); // Índice = 0
        playlist.adicionar(b); // Índice = 1
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.removerNaPosicao(2));
    }

    // ----- Bônus: getDuracaoTotalSegundos e reproduzirTudo -----

    @Test
    @DisplayName("Duração total é a soma das durações")
    void duracaoTotal() {
        playlist.adicionar(a); // 200
        playlist.adicionar(b); // 180
        assertEquals(380, playlist.getDuracaoTotalSegundos());
    }

    @Test
    @DisplayName("reproduzirTudo incrementa todas as músicas")
    void reproduzirTudoIncrementaTodas() {
        playlist.adicionar(a);
        playlist.adicionar(b);
        playlist.reproduzirTudo();
        assertEquals(1, a.getReproducoes());
        assertEquals(1, b.getReproducoes());
    }
}
