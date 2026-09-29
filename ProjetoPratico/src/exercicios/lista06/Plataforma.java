package exercicios.lista06;

import java.util.ArrayList;

public class Plataforma {

    private ArrayList<Musica> musicas;

    private ArrayList<Usuario> usuarios;

    public Plataforma() {
        this.musicas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    /** @return true se cadastrou; false se a musica for nula ou o acervo estiver cheio. */
    public boolean cadastrarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }
        return musicas.add(musica);
    }

    /** @return true se cadastrou; false se o usuario for nulo ou o acervo estiver cheio. */
    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        return usuarios.add(usuario);
    }

    /**
     * Busca por id (sobrecarga que recebe int).
     * Fase 02: procurar e nao encontrar e uma resposta valida da busca,
     * nao uma anomalia. Continua devolvendo null.
     * @return a musica ou null se nao encontrar.
     */
    public Musica buscarMusicaPorId(int id) {
        for (Musica musica : musicas) {
            if (musica.getId() == id) {
                return musica;
            }
        }
        return null;
    }

    /** Busca pela primeira musica com o titulo informado (sobrecarga que recebe String). */
    public Musica buscarMusica(String titulo) {
        for (Musica musica : musicas) {
            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                return musica;
            }
        }
        return null;
    }

    public int getTotalMusicas() {
        return musicas.size();
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    // ---- Auxiliares de leitura usados pela App para listar (nao pedidos no enunciado) ----

    public Musica getMusicaNoIndice(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            return null;
        }
        return musicas.get(indice);
    }

    public Usuario getUsuarioNoIndice(int indice) {
        if (indice < 0 || indice >= usuarios.size()) {
            return null;
        }
        return usuarios.get(indice);
    }
}
