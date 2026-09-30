package exemplos.unidade04.heranca.abstracao;

public class NotificacaoApp extends Notificacao {

    public NotificacaoApp(String titulo, String destinatario) {
        super(titulo, destinatario);
    }

   @Override
   public void disparaNotificacao() {
       System.out.println("Envio de notificação via APP");
   }


}
