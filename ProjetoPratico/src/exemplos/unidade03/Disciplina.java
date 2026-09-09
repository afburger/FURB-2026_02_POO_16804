package exemplos.unidade03;

import java.util.ArrayList;

public class Disciplina {

    private String nome;
    private ArrayList<Aluno> alunos;

    public Disciplina(Aluno aluno) {
        alunos = new ArrayList<>();
        adicionarAluno(aluno);
    }

    public Disciplina(ArrayList<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }
}
