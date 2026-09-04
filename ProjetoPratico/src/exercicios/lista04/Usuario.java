package exercicios.lista04;

public class Usuario {

    private static int contador = 0;

    private int id;
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        // Fase 02: valida primeiro, atribui depois.
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido. O nome não pode ser vazio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail inválido. O e-mail não pode ser vazio.");
        }
        // Nao valida e-mail de verdade: basta conter um @ no meio.
        if (!email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido: " + email + ". O e-mail deve conter @.");
        }

        this.nome = nome;
        this.email = email;
        this.id = contador;
        contador++;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
