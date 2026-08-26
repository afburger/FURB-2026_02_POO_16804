package exemplos.unidade01.tratamentoExcecoes;

public class Estacionamento {

    public static void main(String[] args) {
        Carro[] carros = new Carro[15];
        
        try {
            Carro carro1 = new Carro("LLL-2222", "Fusca", 21, 28);
            carros[0] = carro1;
            
            Carro carro2 = new Carro(null, "Kombi", 21, 28);
            carros[1] = carro2;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
         
        for (int i = 0; i < carros.length; i++) {
            try {
                System.out.println("Carro " + i + ": " + carros[i].getPlaca());
            } catch (NullPointerException e) {
                System.out.println("Vaga " + i + " disponível");
            }
        }
        

    }

}
