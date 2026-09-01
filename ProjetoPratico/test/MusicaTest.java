import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exercicios.lista04.Musica;

public class MusicaTest {

    private static Musica musica;

    @BeforeAll
    public static void inicializaMusica() {
        musica = new Musica("Titulo", "Artista", 120);
    }

    @Test
    public void testCriacaoMusica() {
        assertNotNull(musica);
    }

    @Test
    public void testTituloInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            Musica mus = new Musica("", "Artista", 150);
        });
    }

    @Test
    public void testDuracaoValida() {
        assertEquals(120, musica.getDuracaoEmSegundos());
    }

    @Test
    public void testDuracaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            Musica mus = new Musica("Titulo", "Artista", -150);
        });
    }

}
