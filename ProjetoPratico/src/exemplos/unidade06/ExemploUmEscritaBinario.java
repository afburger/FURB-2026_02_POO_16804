package exemplos.unidade06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExemploUmEscritaBinario {

    public static void main(String[] args) {
        File arquivo;
        FileOutputStream fos;
        try {
            arquivo = new File("dados.dat");
            fos = new FileOutputStream(arquivo, true);
            //fos.write(79);
            //fos.write(105);
            fos.write(65);

            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo ou diretório não encontrado.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } 
    }

}
