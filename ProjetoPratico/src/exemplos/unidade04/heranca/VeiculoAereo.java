package exemplos.unidade04.heranca;

public class VeiculoAereo extends Veiculo {

    public VeiculoAereo() {
        super(0);
    }

    @Override
    public String toString() {
        return "Veículo aéreo: " + super.toString();
    }

}
