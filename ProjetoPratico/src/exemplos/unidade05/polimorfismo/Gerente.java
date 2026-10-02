package exemplos.unidade05.polimorfismo;

public class Gerente extends Funcionario {

    private double percentual;

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public double getPercentual() {
        return percentual;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + getSalarioBase() * percentual;
    }

}
