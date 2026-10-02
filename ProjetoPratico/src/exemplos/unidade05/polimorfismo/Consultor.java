package exemplos.unidade05.polimorfismo;

public class Consultor extends Funcionario{

    private int quantidadeViagens;

    public void setQuantidadeViagens(int quantidadeViagens) {
        this.quantidadeViagens = quantidadeViagens;
    }

    public int getQuantidadeViagens() {
        return quantidadeViagens;
    }

    @Override
    public double calcularSalario() {
        double salario = super.calcularSalario();
        double viagens = 500 * quantidadeViagens;
        return  salario + viagens;
    }

}
