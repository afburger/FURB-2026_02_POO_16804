package exemplos.unidade01.construtores;

public class Carro {

    private String modelo;
    private int ano;
    private String cor;
    private boolean documentoEmDia;
    private String marca;

    public Carro() {
        documentoEmDia = true;
    }

    public Carro(String modelo, int ano, String cor) {
        this(modelo, ano, cor, "Indifente");
    }

    public Carro(String modelo, int ano, String cor, String marca) {
        this();
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isDocumentoEmDia() {
        return documentoEmDia;
    }

    public void setDocumentoEmDia(boolean documentoEmDia) {
        this.documentoEmDia = documentoEmDia;
    }

    public String getMarca() {
        return marca;
    }

}
