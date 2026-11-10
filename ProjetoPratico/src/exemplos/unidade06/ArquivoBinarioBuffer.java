package exemplos.unidade06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo 4: arquivo BINÁRIO usando buffer.
 *
 * Gravação: FileOutputStream -> BufferedOutputStream -> DataOutputStream
 * Leitura:  FileInputStream  -> BufferedInputStream  -> DataInputStream
 *
 * O arquivo gerado é idêntico ao do Exemplo 3. A diferença é que os dados
 * ficam acumulados em um buffer de 8 KB na memória e vão para o disco em
 * blocos, reduzindo muito a quantidade de acessos ao dispositivo.
 */
public class ArquivoBinarioBuffer {

    private final File arquivo;

    public ArquivoBinarioBuffer(String caminho) {
        this.arquivo = new File(caminho);
    }

    public static void main(String[] args) throws IOException {
        ArquivoBinarioBuffer arq = new ArquivoBinarioBuffer("produtos_buffer.dat");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(10, "Mouse sem fio", 89.90));
        produtos.add(new Produto(20, "Teclado mecânico", 349.00));
        arq.gravar(produtos);

        arq.acrescentar(new Produto(30, "Monitor 24 polegadas", 899.50));

        System.out.println("Conteúdo de produtos_buffer.dat:");
        for (Produto p : arq.ler()) {
            System.out.println(p);
        }
        System.out.println("Tamanho do arquivo: " + new File("produtos_buffer.dat").length() + " bytes");

        // Teste de desempenho: compare com o tempo do ArquivoBinarioDireto
        medirTempo(new ArquivoBinarioBuffer("muitos_produtos_buffer.dat"), 100_000);
    }

    /**
     * CRIAÇÃO: recria o arquivo com a lista de produtos.
     */
    public void gravar(List<Produto> produtos) throws IOException {
        // Usamos os métodos do objeto mais externo (DataOutputStream).
        // O close() dele faz o flush do buffer e fecha toda a cadeia.
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo)))) {
            for (Produto p : produtos) {
                escrever(dos, p);
            }
        }
    }

    /**
     * ALTERAÇÃO: acrescenta um produto no final (append = true).
     */
    public void acrescentar(Produto p) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo, true)))) {
            escrever(dos, p);
        }
    }

    /**
     * LEITURA: lê os produtos até o fim do arquivo.
     */
    public List<Produto> ler() throws IOException {
        List<Produto> produtos = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(arquivo)))) {
            while (true) {
                try {
                    produtos.add(lerProduto(dis));
                } catch (EOFException e) {
                    break; // fim do arquivo
                }
            }
        }
        return produtos;
    }

    private void escrever(DataOutputStream dos, Produto p) throws IOException {
        dos.writeInt(p.getCodigo());
        dos.writeUTF(p.getDescricao());
        dos.writeDouble(p.getPreco());
    }

    private Produto lerProduto(DataInputStream dis) throws IOException {
        int codigo = dis.readInt();
        String descricao = dis.readUTF();
        double preco = dis.readDouble();
        return new Produto(codigo, descricao, preco);
    }

    static void medirTempo(ArquivoBinarioBuffer arq, int quantidade) throws IOException {
        List<Produto> muitos = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            muitos.add(new Produto(i, "Produto " + i, i * 1.5));
        }

        long inicio = System.currentTimeMillis();
        arq.gravar(muitos);
        long meio = System.currentTimeMillis();
        arq.ler();
        long fim = System.currentTimeMillis();

        System.out.println("\nCOM buffer, " + quantidade + " produtos:");
        System.out.println("  gravação: " + (meio - inicio) + " ms");
        System.out.println("  leitura:  " + (fim - meio) + " ms");
    }
}
