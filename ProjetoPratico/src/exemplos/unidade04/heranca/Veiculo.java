package exemplos.unidade04.heranca;

public class Veiculo {

    private int anoFabricacao;
    private String marca;
    private String modelo;
    private double valor;

    public Veiculo(double valor) {
        this.valor = valor;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return marca + " - " + modelo;
    }


}
