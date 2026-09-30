package exemplos.unidade04.heranca.abstracao;

public abstract class Notificacao {

    private String titulo;
    private String destinatario;

    public Notificacao(String titulo, String destinatario) {
        this.titulo = titulo;
        this.destinatario = destinatario;
    }

    public abstract void disparaNotificacao();

    public String getDestinatario() {
        return destinatario;
    }

    public String getTitulo() {
        return titulo;
    }

}
