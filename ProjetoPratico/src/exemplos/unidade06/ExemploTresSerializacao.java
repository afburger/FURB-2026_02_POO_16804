package exemplos.unidade06;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ExemploTresSerializacao {

    public static void main(String[] args) {
        File f = new File("Objetos.bin");
        try (FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream o = new ObjectOutputStream(fos)) {
            o.writeObject("Boa noite");
            o.writeObject(new ContaBancaria("1", "Lucas", 300));
            o.writeObject(new Veiculo("MIE-3932", "Anderson"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
