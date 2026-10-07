package exemplos.unidade05.interfaces;

public class Bateria implements EmitirSom {

    @Override 
    public String emitirSom() {
        return "Som de bateria";
    }

}
