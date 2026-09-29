package exercicios.lista08;

import java.util.ArrayList;

public class Usuario {

    private static int contador = 0;

    private int id;
    private String nome;
    private String email;
    private ArrayList<Usuario> seguindo;

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
        this.seguindo = new ArrayList<>();
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

    public void seguir(Usuario outro) {
        if (this.equals(outro)) {
            throw new IllegalArgumentException("Você não pode seguir você mesmo.");
        }
        for (Usuario usuario : seguindo) {
            if (usuario.equals(outro)) {
                throw new IllegalArgumentException("Você já segue esse usuário.");
            }
        }
        seguindo.add(outro);
    }

    public void deixarDeSeguir(Usuario outro) {
        seguindo.remove(outro);
    }

    public int getQuantidadeSeguindo() {
        return seguindo.size();
    }
}
