package exemplos.unidade06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Exemplo 1: arquivo TEXTO, acesso direto ao arquivo.
 *
 * Gravação: PrintWriter    Leitura: Scanner
 *
 * Cada produto vira uma linha no formato:  codigo;descricao;preco
 * Dá para abrir o arquivo no bloco de notas e ler o conteúdo.
 */
public class ArquivoTextoDireto {

    private final File arquivo;

    public ArquivoTextoDireto(String caminho) {
        this.arquivo = new File(caminho);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArquivoTextoDireto arq = new ArquivoTextoDireto("produtos.txt");

        // 1) cria o arquivo com dois produtos
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(10, "Mouse sem fio", 89.90));
        produtos.add(new Produto(20, "Teclado mecânico", 349.00));
        arq.gravar(produtos);

        // 2) acrescenta um terceiro produto no final
        arq.acrescentar(new Produto(30, "Monitor 24 polegadas", 899.50));

        // 3) lê tudo de volta
        System.out.println("Conteúdo de produtos.txt:");
        for (Produto p : arq.ler()) {
            System.out.println(p);
        }
    }
    
    /**
     * CRIAÇÃO: grava a lista de produtos.
     * Se o arquivo já existir, ele é RECRIADO (o conteúdo anterior é perdido).
     */
    public void gravar(List<Produto> produtos) throws FileNotFoundException {
        // try-with-resources: o close() é chamado automaticamente no final,
        // mesmo que aconteça uma exceção no meio da gravação
        try (PrintWriter pw = new PrintWriter(arquivo)) {
            for (Produto p : produtos) {
                pw.println(paraLinha(p));
            }
        }
    }

    /**
     * ALTERAÇÃO: acrescenta um produto no FINAL do arquivo, sem apagar o que já existe.
     * O PrintWriter sozinho sempre recria o arquivo, por isso usamos um
     * FileOutputStream aberto com append = true por baixo dele.
     */
    public void acrescentar(Produto p) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(arquivo, true))) {
            pw.println(paraLinha(p));
        }
    }

    /**
     * LEITURA: lê o arquivo linha a linha e reconstrói os produtos.
     */
    public List<Produto> ler() throws FileNotFoundException {
        List<Produto> produtos = new ArrayList<>();

        try (Scanner sc = new Scanner(arquivo)) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (!linha.isBlank()) {
                    produtos.add(deLinha(linha));
                }
            }
        }
        return produtos;
    }

    // Converte o objeto em uma linha de texto: 10;Mouse sem fio;89.9
    private String paraLinha(Produto p) {
        // Concatenar o double (em vez de usar printf("%.2f")) garante o ponto
        // como separador decimal, independente do idioma do sistema.
        return p.getCodigo() + ";" + p.getDescricao() + ";" + p.getPreco();
    }

    // Faz o caminho inverso: quebra a linha no ";" e converte cada parte
    private Produto deLinha(String linha) {
        String[] partes = linha.split(";");
        int codigo = Integer.parseInt(partes[0]);
        String descricao = partes[1];
        double preco = Double.parseDouble(partes[2]);
        return new Produto(codigo, descricao, preco);
    }

}
