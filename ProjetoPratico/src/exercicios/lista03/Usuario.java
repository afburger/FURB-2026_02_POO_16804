package exercicios.lista03;

public class Usuario {

    private static int contador = 0;

    private int id;
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        contador++;
        id = contador;
        this.nome = nome;
        this.email = email;
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
