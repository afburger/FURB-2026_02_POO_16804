package exemplos.unidade06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExemploDoisLeituraTexto {

    public static void main(String[] args) {
        try {
            File arquivo = new File("texto.txt");
            Scanner sc = new Scanner(arquivo);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
    }

}
