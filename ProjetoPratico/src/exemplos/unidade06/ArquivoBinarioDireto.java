package exemplos.unidade06;

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
 * Exemplo 3: arquivo BINÁRIO, acesso direto ao arquivo.
 *
 * Gravação: FileOutputStream -> DataOutputStream
 * Leitura: FileInputStream -> DataInputStream
 *
 * Cada produto é gravado como: int (4 bytes) + String (writeUTF) + double (8
 * bytes).
 * O arquivo NÃO é legível no bloco de notas.
 *
 * Sem buffer: cada writeInt/writeUTF/writeDouble vai direto para o disco.
 */
public class ArquivoBinarioDireto {

    private final File arquivo;

    public ArquivoBinarioDireto(String caminho) {
        this.arquivo = new File(caminho);
    }

    public static void main(String[] args) throws IOException {
        ArquivoBinarioDireto arq = new ArquivoBinarioDireto("produtos.dat");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(10, "Mouse sem fio", 89.90));
        produtos.add(new Produto(20, "Teclado mecânico", 349.00));
        arq.gravar(produtos);

        arq.acrescentar(new Produto(30, "Monitor 24 polegadas", 899.50));

        System.out.println("Conteúdo de produtos.dat:");
        for (Produto p : arq.ler()) {
            System.out.println(p);
        }
        System.out.println("Tamanho do arquivo: " + new File("produtos.dat").length() + " bytes");

        // Teste de desempenho: compare com o tempo do ArquivoBinarioBuffer
        medirTempo(new ArquivoBinarioDireto("muitos_produtos.dat"), 100_000);
    }

    /**
     * CRIAÇÃO: recria o arquivo com a lista de produtos.
     */
    public void gravar(List<Produto> produtos) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(arquivo))) {
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
                new FileOutputStream(arquivo, true))) {
            escrever(dos, p);
        }
    }

    /**
     * LEITURA: lê os produtos até o fim do arquivo.
     */
    public List<Produto> ler() throws IOException {
        List<Produto> produtos = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(arquivo))) {
            while (true) {
                try {
                    produtos.add(lerProduto(dis));
                } catch (EOFException e) {
                    // EOFException: tentamos ler além do fim do arquivo,
                    // ou seja, não há mais produtos
                    break;
                }
            }
        }
        return produtos;
    }

    // A ORDEM de gravação define a ordem de leitura: int, String, double
    private void escrever(DataOutputStream dos, Produto p) throws IOException {
        dos.writeInt(p.getCodigo());
        dos.writeUTF(p.getDescricao());
        dos.writeDouble(p.getPreco());
    }

    // Lê na MESMA ordem em que foi gravado
    private Produto lerProduto(DataInputStream dis) throws IOException {
        int codigo = dis.readInt();
        String descricao = dis.readUTF();
        double preco = dis.readDouble();
        return new Produto(codigo, descricao, preco);
    }

    static void medirTempo(ArquivoBinarioDireto arq, int quantidade) throws IOException {
        List<Produto> muitos = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            muitos.add(new Produto(i, "Produto " + i, i * 1.5));
        }

        long inicio = System.currentTimeMillis();
        arq.gravar(muitos);
        long meio = System.currentTimeMillis();
        arq.ler();
        long fim = System.currentTimeMillis();

        System.out.println("\nSEM buffer, " + quantidade + " produtos:");
        System.out.println("  gravação: " + (meio - inicio) + " ms");
        System.out.println("  leitura:  " + (fim - meio) + " ms");
    }
}
