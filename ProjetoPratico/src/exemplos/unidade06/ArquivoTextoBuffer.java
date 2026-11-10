package exemplos.unidade06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Exemplo 2: arquivo TEXTO usando buffer de forma explícita.
 *
 * Gravação: FileOutputStream -> BufferedOutputStream -> PrintWriter
 * Leitura:  FileInputStream  -> BufferedInputStream  -> Scanner
 *
 * O arquivo gerado é idêntico ao do Exemplo 1. O que muda é o encadeamento:
 * os dados passam por um buffer na memória antes de ir para o disco.
 *
 * Observação: o PrintWriter(File) e o Scanner(File) do Exemplo 1 já usam um
 * buffer interno. Aqui o buffer aparece no encadeamento para ficar visível
 * e para mostrar o mesmo padrão usado nos arquivos binários.
 */
public class ArquivoTextoBuffer {

    private final File arquivo;

    public ArquivoTextoBuffer(String caminho) {
        this.arquivo = new File(caminho);
    }

    /**
     * CRIAÇÃO: recria o arquivo com a lista de produtos.
     */
    public void gravar(List<Produto> produtos) throws FileNotFoundException {
        // Fechar o objeto mais externo (PrintWriter) fecha toda a cadeia,
        // e o close() faz o flush do buffer antes de fechar.
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo)))) {
            for (Produto p : produtos) {
                pw.println(paraLinha(p));
            }
        }
    }

    /**
     * ALTERAÇÃO: acrescenta um produto no final (append = true no FileOutputStream).
     */
    public void acrescentar(Produto p) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo, true)))) {
            pw.println(paraLinha(p));
        }
    }

    /**
     * LEITURA: o Scanner lê os dados do BufferedInputStream,
     * que busca blocos de 8 KB no disco de uma vez.
     */
    public List<Produto> ler() throws FileNotFoundException {
        List<Produto> produtos = new ArrayList<>();

        try (Scanner sc = new Scanner(
                new BufferedInputStream(
                        new FileInputStream(arquivo)))) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (!linha.isBlank()) {
                    produtos.add(deLinha(linha));
                }
            }
        }
        return produtos;
    }

    private String paraLinha(Produto p) {
        return p.getCodigo() + ";" + p.getDescricao() + ";" + p.getPreco();
    }

    private Produto deLinha(String linha) {
        String[] partes = linha.split(";");
        return new Produto(Integer.parseInt(partes[0]), partes[1], Double.parseDouble(partes[2]));
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArquivoTextoBuffer arq = new ArquivoTextoBuffer("produtos_buffer.txt");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(10, "Mouse sem fio", 89.90));
        produtos.add(new Produto(20, "Teclado mecânico", 349.00));
        arq.gravar(produtos);

        arq.acrescentar(new Produto(30, "Monitor 24 polegadas", 899.50));

        System.out.println("Conteúdo de produtos_buffer.txt:");
        for (Produto p : arq.ler()) {
            System.out.println(p);
        }
    }
}
