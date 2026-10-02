package exemplos.unidade05.polimorfismo;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        Funcionario f = new Programador();
        f.setSalarioBase(3500);

        Funcionario g = new Gerente();
        g.setSalarioBase(10000);

        Funcionario p = new Programador();
        p.setSalarioBase(5500);

        ArquitetoSoftware as = new ArquitetoSoftware();
        as.setSalarioBase(9000);
        as.adicionarLinguagem("Java");
        as.adicionarLinguagem("C#");

        Consultor c = new Consultor();
        c.setQuantidadeViagens(10);
        c.setSalarioBase(4000);

        funcionarios.add(f);
        funcionarios.add(g);
        funcionarios.add(p);
        funcionarios.add(as);
        funcionarios.add(c);

        Programador prog = new ArquitetoSoftware();

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.calcularSalario());
            if (funcionario instanceof Consultor) {
                System.out.println(((Consultor) funcionario).getQuantidadeViagens());
            }
        }

    }

}
