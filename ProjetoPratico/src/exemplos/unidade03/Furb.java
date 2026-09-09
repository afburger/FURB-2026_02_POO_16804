package exemplos.unidade03;

public class Furb {

    public static void main(String[] args) {
        Professor andre = new Professor();
        andre.setNome("André");

        Professor aurelio = new Professor();
        aurelio.setNome("Aurélio");

        Professor valdameri = new Professor();
        valdameri.setNome("Valdameri");
        aurelio.adicionarSubordinado(valdameri);

        aurelio.adicionarSubordinado(andre);


        Pessoa autor = new Pessoa();

        //for (int i = 0; i < aurelio.getSubordinados().size(); i++) {
           // System.out.println(aurelio.getSubordinados().get(i).getNome());
        //}

        for (Professor prof : aurelio.getSubordinados()) {
            System.out.println(prof.getNome());
        }

        System.out.println("Coordenador do André: " + andre.getCordenador().getNome());
        System.out.println("Coordenador do Valdameri: " + valdameri.getCordenador().getNome());
    }

}
