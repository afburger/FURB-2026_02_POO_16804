package exemplos.unidade04.heranca.abstracao;

public class App {

    public static void main(String[] args) {

        // Não é uma instância de classe abstrata e sim declarando uma classe anônima,
        // ou seja, é como se estivesse criando uma nova classe e dizendo que a mesma herda de notificação e fazendo a implementação do método abstrato.
        Notificacao not = new Notificacao("um", "dois") {
            @Override
            public void disparaNotificacao() {
                System.out.println("Teste");
                
            }
        };

        NotificacaoEmail email = new NotificacaoEmail("Titulo", "Dest", "email", "assunto", "remetente");
        NotificacaoApp app = new NotificacaoApp("titulo APP", "destinatario APP");
        
        email.disparaNotificacao();
        app.disparaNotificacao();
    }

}
