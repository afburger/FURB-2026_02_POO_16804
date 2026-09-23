package exemplos.unidade04.heranca;

public class MotoEletrica extends VeiculoTerrestre {

    public MotoEletrica(int anoFabricacao, double valor) {
        super(anoFabricacao, "Genérica", "Padrão", valor);
    }

    @Override
    public String toString() {
        return super.toString() + " (moto elétrica)";
    }

}
