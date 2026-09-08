

import org.junit.jupiter.api.Test;

import exercicios.lista04.Musica;
import exercicios.lista04.Plataforma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PlataformaTest {

    private Plataforma plataforma;
    private Musica faixa1;

    @BeforeEach
    void montarCenario() {
        plataforma = new Plataforma();
        faixa1 = new Musica("Faixa 1", "Artista 1", 200);
        plataforma.cadastrarMusica(faixa1);
        plataforma.cadastrarMusica(new Musica("Faixa 2", "Artista 2", 180));
    }

    // ----- PL06: buscarMusica / buscarMusicaPorId -----

    @Test
    @DisplayName("Buscar por título existente devolve a música")
    void buscarTituloExistente() {
        Musica encontrada = plataforma.buscarMusica("Faixa 1");
        assertNotNull(encontrada);
        assertEquals("Faixa 1", encontrada.getTitulo());
    }

    @Test
    @DisplayName("Buscar por título inexistente devolve null")
    void buscarTituloInexistente() {
        assertNull(plataforma.buscarMusica("Inexistente"));
    }

    @Test
    @DisplayName("Buscar por id existente devolve a música")
    void buscarPorIdExistente() {
        Musica encontrada = plataforma.buscarMusicaPorId(faixa1.getId());
        assertNotNull(encontrada);
        assertEquals(faixa1.getId(), encontrada.getId());
    }

    @Test
    @DisplayName("Buscar por id inexistente devolve null")
    void buscarPorIdInexistente() {
        assertNull(plataforma.buscarMusicaPorId(999999));
    }
}
