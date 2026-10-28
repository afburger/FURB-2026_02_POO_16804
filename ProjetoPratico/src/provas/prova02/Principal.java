package provas.prova02;

public class Principal {

    public static void main(String[] args) {
        // Letra A
        Cliente c = new Cliente("Andre", "9999-9999");
        Pedido p = new Pedido(c, "Rua XV de Novembro", 1200);

        // Letra B
        ItemCardapio i1 = new Prato("Risoto de cogumelos", 45, 2);
        ItemCardapio i2 = new Bebida("Suco de uva", 10, 6);
        ItemCardapio i3 = new Bebida("Água com gás", 5, 2);

        p.adicionarItem(i1);
        p.adicionarItem(i2);
        p.adicionarItem(i3);

        // Letra C
        for (ItemCardapio item : p.getItens()) {
            System.out.println(item);
        }

        // Letra D
        System.out.println("Rua de entrega: " + p.getRuaEntrega());
        System.out.println("Nome comprador: " + p.getComprador().getNome());
        System.out.println("Valor total: " + p.calcularValorTotal());
        System.out.println("Total de impostos " + p.calcularTotalImpostos());

        // Letra E
        try {
            p.adicionarItem(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
