package exemplos.unidade03;

import java.util.ArrayList;

public class Escola {

    public static void main(String[] args) {
        Aluno andre = new Aluno();
        andre.setNome("André");

        Aluno joao = new Aluno();
        joao.setNome("João");

        ArrayList<Aluno> alunosFurb = new ArrayList<>();
        alunosFurb.add(andre);
        alunosFurb.add(joao);


        Disciplina poo = new Disciplina(alunosFurb);
        poo.setNome("POO");


    }

}
