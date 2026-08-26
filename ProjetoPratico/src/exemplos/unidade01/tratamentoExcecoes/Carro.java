package exemplos.unidade01.tratamentoExcecoes;

public class Carro {

    private String placa;
    private String modelo;
    private int hora;
    private int minuto;

    public Carro(String placa, String modelo, int hora, int minuto) {
        if (placa == null) {
            throw new IllegalArgumentException("Parâmetro vazio");
        }
        
        this.placa = placa;
        this.modelo = modelo;
        this.hora = hora;
        this.minuto = minuto;
    }

    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public int getHora() {
        return hora;
    }
    public int getMinuto() {
        return minuto;
    }

    

}
