package exemplos.unidade05.interfaces;

public class Elefante implements Animal {

    @Override
    public String emitirSom() {
        return "Som de elefante";
    }

    @Override
    public int getQuantidadePatas() {
        return 4;
    }

}
