

import org.junit.jupiter.api.Test;

import exercicios.lista04.Musica;
import exercicios.lista04.Usuario;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class MusicaTest {

    // ----- PL01: getDuracaoFormatada -----

    @Test
    @DisplayName("Duração com minutos e segundos")
    void duracaoComMinutosESegundos() {
        Musica m = new Musica("Faixa", "Artista", 125);
        assertEquals("02:05", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração redonda em minutos")
    void duracaoRedondaEmMinutos() {
        Musica m = new Musica("Faixa", "Artista", 90);
        assertEquals("01:30", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Menos de um minuto, com zero à esquerda")
    void duracaoMenorQueUmMinuto() {
        Musica m = new Musica("Faixa", "Artista", 5);
        assertEquals("00:05", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Dois dígitos nos minutos")
    void duracaoDoisDigitosMinutos() {
        Musica m = new Musica("Faixa", "Artista", 600);
        assertEquals("10:00", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Valor logo abaixo de dez minutos")
    void duracaoLogoAbaixoDeDezMinutos() {
        Musica m = new Musica("Faixa", "Artista", 599);
        assertEquals("09:59", m.getDuracaoFormatada());
    }

    // ----- PL02: construtor com dados inválidos -----

    @Test
    @DisplayName("Título vazio deve ser rejeitado")
    void tituloVazioLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("", "Queen", 355));
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado")
    void tituloNuloLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica(null, "Queen", 355));
    }

    @Test
    @DisplayName("Artista vazio deve ser rejeitado")
    void artistaVazioLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "", 355));
    }

    @Test
    @DisplayName("Duração zero deve ser rejeitada")
    void duracaoZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "Queen", 0));
    }

    @Test
    @DisplayName("Duração negativa deve ser rejeitada")
    void duracaoNegativaLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "Queen", -10));
    }

    @Test
    @DisplayName("Dados válidos criam a música")
    void dadosValidosCriamMusica() {
        Musica m = new Musica("Bohemian Rhapsody", "Queen", 355);
        assertNotNull(m);
        assertTrue(m.getId() > 0);
    }

    // ----- PL07: reproduzir -----

    @Test
    @DisplayName("Música recém-criada começa com zero reproduções")
    void estadoInicialSemReproducoes() {
        Musica m = new Musica("Faixa", "Artista", 200);
        assertEquals(0, m.getReproducoes());
    }

    @Test
    @DisplayName("Uma reprodução incrementa o contador em um")
    void umaReproducaoIncrementa() {
        Musica m = new Musica("Faixa", "Artista", 200);
        m.reproduzir();
        assertEquals(1, m.getReproducoes());
    }

    @Test
    @DisplayName("Três reproduções resultam em contador igual a três")
    void tresReproducoes() {
        Musica m = new Musica("Faixa", "Artista", 200);
        m.reproduzir();
        m.reproduzir();
        m.reproduzir();
        assertEquals(3, m.getReproducoes());
    }

    // ----- PL08: contadores de id -----

    @Test
    @DisplayName("Ids de Musica são sequenciais")
    void idsSequenciais() {
        Musica m1 = new Musica("Faixa 1", "Artista", 200);
        Musica m2 = new Musica("Faixa 2", "Artista", 200);
        assertEquals(m2.getId(), m1.getId() + 1);
    }

    @Test
    @DisplayName("Contador de Musica não é afetado pela criação de Usuario")
    void contadoresIndependentes() {
        Musica m1 = new Musica("Faixa A", "Artista", 200);
        new Usuario("Ana", "ana@email.com");
        Musica m2 = new Musica("Faixa B", "Artista", 200);
        assertEquals(m2.getId(), m1.getId() + 1);
    }
}
