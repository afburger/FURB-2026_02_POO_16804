package exemplos.unidade05.interfaces;

public abstract class InstrumentosDeCorda implements EmitirSom {

    private int quantidadeCordas;

    public InstrumentosDeCorda(int quantidadeCordas) {
        this.quantidadeCordas = quantidadeCordas;
    }

    public int getQuantidadeCordas() {
        return quantidadeCordas;
    }

}
