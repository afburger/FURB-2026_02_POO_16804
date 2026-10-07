package exemplos.unidade05.interfaces;

public class Violao extends InstrumentosDeCorda {

    public Violao() {
        super(6);
    }

    @Override
    public String emitirSom() {
        return "Som de violão";
    }

}
