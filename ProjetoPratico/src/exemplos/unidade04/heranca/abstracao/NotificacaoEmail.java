package exemplos.unidade04.heranca.abstracao;

public class NotificacaoEmail extends Notificacao {

    private String email;
    private String assunto;
    private String remetente;

    public NotificacaoEmail(String titulo, String destinatario, String email, String assunto, String remetente) {
        super(titulo, destinatario);
        this.email = email;
        this.assunto = assunto;
        this.remetente = remetente;
    }

    @Override
    public void disparaNotificacao() {
        System.out.println("Envio de notificação via e-mail");
    }

}
