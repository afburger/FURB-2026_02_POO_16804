package exercicios.lista04;

public class Plataforma {

    private static final int CAPACIDADE = 500;

    private Musica[] musicas;
    private int totalMusicas;

    private Usuario[] usuarios;
    private int totalUsuarios;

    public Plataforma() {
        this.musicas = new Musica[CAPACIDADE];
        this.totalMusicas = 0;
        this.usuarios = new Usuario[CAPACIDADE];
        this.totalUsuarios = 0;
    }

    /** @return true se cadastrou; false se a musica for nula ou o acervo estiver cheio. */
    public boolean cadastrarMusica(Musica musica) {
        if (musica == null || totalMusicas >= CAPACIDADE) {
            return false;
        }
        musicas[totalMusicas] = musica;
        totalMusicas++;
        return true;
    }

    /** @return true se cadastrou; false se o usuario for nulo ou o acervo estiver cheio. */
    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario == null || totalUsuarios >= CAPACIDADE) {
            return false;
        }
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        return true;
    }

    /**
     * Busca por id (sobrecarga que recebe int).
     * Fase 02: procurar e nao encontrar e uma resposta valida da busca,
     * nao uma anomalia. Continua devolvendo null.
     * @return a musica ou null se nao encontrar.
     */
    public Musica buscarMusicaPorId(int id) {
        for (int i = 0; i < totalMusicas; i++) {
            if (musicas[i].getId() == id) {
                return musicas[i];
            }
        }
        return null;
    }

    /** Busca pela primeira musica com o titulo informado (sobrecarga que recebe String). */
    public Musica buscarMusica(String titulo) {
        for (int i = 0; i < totalMusicas; i++) {
            if (musicas[i].getTitulo().equalsIgnoreCase(titulo)) {
                return musicas[i];
            }
        }
        return null;
    }

    public int getTotalMusicas() {
        return totalMusicas;
    }

    public int getTotalUsuarios() {
        return totalUsuarios;
    }

    // ---- Auxiliares de leitura usados pela App para listar (nao pedidos no enunciado) ----

    public Musica getMusicaNoIndice(int indice) {
        if (indice < 0 || indice >= totalMusicas) {
            return null;
        }
        return musicas[indice];
    }

    public Usuario getUsuarioNoIndice(int indice) {
        if (indice < 0 || indice >= totalUsuarios) {
            return null;
        }
        return usuarios[indice];
    }
}
