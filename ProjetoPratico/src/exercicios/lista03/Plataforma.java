package exercicios.lista03;

public class Plataforma {

    private static int QTD_ELEMENTOS = 500;

    private Musica[] musicas = new Musica[QTD_ELEMENTOS];
    private int totalMusicas;

    private Usuario[] usuarios = new Usuario[QTD_ELEMENTOS];
    private int totalUsuarios;

    public int getTotalMusicas() {
        return totalMusicas;
    }

    public int getTotalUsuarios() {
        return totalUsuarios;
    }

    public boolean cadastrarMusica(Musica musica) {
        if (musica == null || totalMusicas >= QTD_ELEMENTOS) {
            return false;
        }
        musicas[totalMusicas] = musica;
        totalMusicas++;
        return true;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario == null || totalUsuarios >= QTD_ELEMENTOS) {
            return false;
        }
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        return true;
    }

    public Musica buscarMusicaPorId(int id) {
        for (int i = 0; i < totalMusicas; i++) {
            Musica musica = musicas[i];
            if (musica.getId() == id) {
                return musica;
            }
        }
        return null;
    }

    public Musica buscarMusica(String titulo) {
        for (int i = 0; i < totalMusicas; i++) {
            Musica musica = musicas[i];
            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                return musica;
            }
        }
        return null;
    }

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
