package exemplos.unidade05.interfaces;

public class Baixo extends InstrumentosDeCorda implements Eletronico {

    public Baixo() {
        super(4);
    }

    @Override
    public String emitirSom() {
        return "Som de baixo";
    }

    @Override
    public boolean isEletronico() {
        return true;
    }

}
