package exemplos.unidade04.heranca.abstracao;

public class NotificacaoSms extends NotificacaoTelefone {
    

    
    public NotificacaoSms(String titulo, String destinatario, String numeroTelefone) {
        super(titulo, destinatario, numeroTelefone);
    }

    @Override
    public void disparaNotificacao() {
       System.out.println("Envio de notificação via SMS");
    }

}
