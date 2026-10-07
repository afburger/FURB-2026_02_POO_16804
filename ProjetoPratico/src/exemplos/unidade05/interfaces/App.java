package exemplos.unidade05.interfaces;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        Cachorro c = new Cachorro();
        Bateria b = new Bateria();
        Gato gato = new Gato();

        ArrayList<EmitirSom> objs = new ArrayList<>();

        objs.add(c);
        objs.add(b);
        objs.add(gato);

        for (EmitirSom obj : objs) {
            System.out.println(obj.emitirSom());
        }

        EmitirSom emitirSom = gato;
        Animal animal = gato;
        TemSentimento temSentimento = gato;

    }

}
