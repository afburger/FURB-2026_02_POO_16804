package exemplos.unidade04.heranca;

public class VeiculoTerrestre extends Veiculo {

    public VeiculoTerrestre(int anoFabricacao, String marca, String modelo, double valor) {
        super(valor);
        setAnoFabricacao(anoFabricacao);
        setMarca(marca);
        setModelo(modelo);
    }

    @Override
    public String toString() {
        return "Tipo terrestre: " + super.toString();
    }

}
