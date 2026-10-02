package exemplos.unidade05.polimorfismo;

import java.util.ArrayList;

public class Programador extends Funcionario{

    private ArrayList<String> linguagens = new ArrayList<>();

    public ArrayList<String> getLinguagens() {
        return linguagens;
    }

    public void adicionarLinguagem(String linguagem) {
        linguagens.add(linguagem);
    }

}
