package exemplos.unidade07.jogos;

/**
 * CAMADA DE NEGÓCIO
 *
 * Representa um jogo da estante. Esta classe não sabe nada de tela (Swing)
 * nem de arquivo: ela só guarda os dados e garante as regras de negócio.
 * Por isso ela poderia ser usada igualzinha num teste JUnit, numa tela web
 * ou num programa de console.
 */
public class Jogo {

    private String titulo;
    private String plataforma;
    private int horasJogadas;

    public Jogo(String titulo, String plataforma, int horasJogadas) {
        // O construtor reaproveita as validações dos setters
        setTitulo(titulo);
        setPlataforma(plataforma);
        setHorasJogadas(horasJogadas);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }
        this.titulo = titulo.trim();
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        if (plataforma == null || plataforma.isBlank()) {
            throw new IllegalArgumentException("A plataforma é obrigatória.");
        }
        this.plataforma = plataforma.trim();
    }

    public int getHorasJogadas() {
        return horasJogadas;
    }

    public void setHorasJogadas(int horasJogadas) {
        if (horasJogadas < 0) {
            throw new IllegalArgumentException("As horas jogadas não podem ser negativas.");
        }
        this.horasJogadas = horasJogadas;
    }

    @Override
    public String toString() {
        return titulo + " (" + plataforma + ") - " + horasJogadas + "h jogadas";
    }
}
