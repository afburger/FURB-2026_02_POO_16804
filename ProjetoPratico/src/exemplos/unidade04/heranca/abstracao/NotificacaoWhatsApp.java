package exemplos.unidade04.heranca.abstracao;

public class NotificacaoWhatsApp extends NotificacaoTelefone {

    private String usuario;
    

    public NotificacaoWhatsApp(String titulo, String destinatario, String numeroTelefone, String usuario) {
        super(titulo, destinatario, numeroTelefone);
        this.usuario = usuario;
    }

    @Override
    public void disparaNotificacao() {
        System.out.println("Envio de notificação via WhatsApp");
    }

    public String getUsuario() {
        return usuario;
    }

}
