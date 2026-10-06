package exercicios.lista09;

public class PlanoIndividual extends PlanoPago {

    public PlanoIndividual(double precoMensal) {
        super("Individual", 1, precoMensal);
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal();
    }

}
