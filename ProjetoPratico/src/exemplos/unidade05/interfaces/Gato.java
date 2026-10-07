package exemplos.unidade05.interfaces;

public class Gato implements Animal, TemSentimento {

    @Override
    public String emitirSom() {
        return "Som de gato";
    }

    @Override
    public int getQuantidadePatas() {
        return 4;
    }

    @Override
    public String getTipoSentimento() {
        return "Carente";
    }

}
