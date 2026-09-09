package exemplos.unidade03;

import java.util.ArrayList;

public class Professor {

    private String nome;
    private Professor cordenador;
    private ArrayList<Professor> subordinados = new ArrayList<>();

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Professor getCordenador() {
        return cordenador;
    }
    public void setCordenador(Professor cordenador) {
        this.cordenador = cordenador;
    }

    public ArrayList<Professor> getSubordinados() {
        return subordinados;
    }

    public void adicionarSubordinado(Professor subordinado) {
        subordinados.add(subordinado);
        subordinado.setCordenador(this);
    }
    

}
