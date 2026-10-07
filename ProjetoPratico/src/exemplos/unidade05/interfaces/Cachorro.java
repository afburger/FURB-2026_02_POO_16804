package exemplos.unidade05.interfaces;

public class Cachorro implements Animal, TemSentimento {

    @Override 
    public String emitirSom() {
        return "Som de cachorro";
    }

    @Override
    public int getQuantidadePatas() {
        return 4;
    }

    @Override
    public String getTipoSentimento() {
        return "Alegria";
    }

}
