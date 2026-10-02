package exemplos.unidade05.polimorfismo;

public class ArquitetoSoftware extends Programador {

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + (getLinguagens().size() * 200);
    }

}
