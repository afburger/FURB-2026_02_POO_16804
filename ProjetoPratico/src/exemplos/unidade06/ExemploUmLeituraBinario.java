package exemplos.unidade06;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExemploUmLeituraBinario {

    public static void main(String[] args) {
        try {
            File f = new File("dados.dat");
            FileInputStream fis = new FileInputStream(f);
            int dado;

            while (true) {
                dado = fis.read();
                if (dado != -1) {
                    System.out.println(dado);
                } else {
                    break;
                }
            }
            fis.close();
        } catch (IOException e) {
            System.out.println("Aconteceu um erro: " + e.getMessage());
        }

        System.out.println("Terminou");

    }

}
