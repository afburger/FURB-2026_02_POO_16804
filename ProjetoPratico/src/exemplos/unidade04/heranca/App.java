package exemplos.unidade04.heranca;

public class App {

    public static void main(String[] args) {
        
        Veiculo v = new Veiculo(759000.00);
        v.setAnoFabricacao(2026);
        v.setMarca("Ford");
        v.setModelo("Mustang");

        System.out.println(v);

        VeiculoAereo va = new VeiculoAereo();
        va.setAnoFabricacao(1979);
        va.setMarca("Embraero");
        va.setModelo("Monomotor");

        System.out.println(va);

        Carro carro = new Carro(2026, "Porsche", "911", 1200000);
        System.out.println(carro);

        MotoEletrica moto = new MotoEletrica(2026, 18000);
        System.out.println(moto);

    }

}
