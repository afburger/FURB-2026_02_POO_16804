package exemplos.unidade06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ExemploDoisEscritaTexto {

    public static void main(String[] args) {
        try {
            File arquivo = new File("texto.txt");
            PrintWriter arquivoTexto = new PrintWriter(arquivo);
            arquivoTexto.println("Primeira linha");
            arquivoTexto.print(true);
            arquivoTexto.printf("Preço: %20.2f", 1234.5);
            arquivoTexto.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
