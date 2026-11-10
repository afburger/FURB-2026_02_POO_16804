package exemplos.unidade06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo 5: SERIALIZAÇÃO BINÁRIA de objetos.
 *
 * Gravação: FileOutputStream -> BufferedOutputStream -> ObjectOutputStream
 * Leitura:  FileInputStream  -> BufferedInputStream  -> ObjectInputStream
 *
 * Diferença para o Exemplo 4: não gravamos campo a campo (writeInt,
 * writeUTF...). O writeObject() grava o OBJETO inteiro, com todos os seus
 * atributos, e o readObject() reconstrói o objeto.
 *
 * Aqui gravamos a LISTA inteira com um único writeObject(). Funciona porque
 * ArrayList também implementa Serializable, assim como Produto.
 */
public class ArquivoSerializacaoBinaria {

    private final File arquivo;

    public ArquivoSerializacaoBinaria(String caminho) {
        this.arquivo = new File(caminho);
    }

    /**
     * CRIAÇÃO: serializa a lista de produtos, recriando o arquivo.
     */
    public void gravar(List<Produto> produtos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo)))) {
            oos.writeObject(produtos);
        }
    }

    /**
     * ALTERAÇÃO: acrescenta um produto.
     *
     * NÃO usamos FileOutputStream(arquivo, true) aqui. Cada ObjectOutputStream
     * novo grava um cabeçalho no arquivo, e ao ler um arquivo com dois
     * cabeçalhos ocorre StreamCorruptedException.
     *
     * Solução: ler a lista, alterar na memória e gravar tudo de novo.
     */
    public void acrescentar(Produto p) throws IOException, ClassNotFoundException {
        List<Produto> produtos = arquivo.exists() ? ler() : new ArrayList<>();
        produtos.add(p);
        gravar(produtos);
    }

    /**
     * LEITURA: desserializa a lista de produtos.
     *
     * ClassNotFoundException: lançada se a classe do objeto gravado
     * não existir no programa que está lendo.
     */
    @SuppressWarnings("unchecked")
    public List<Produto> ler() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(arquivo)))) {
            // readObject() devolve Object: é preciso fazer o cast
            return (List<Produto>) ois.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArquivoSerializacaoBinaria arq = new ArquivoSerializacaoBinaria("produtos.ser");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(10, "Mouse sem fio", 89.90));
        produtos.add(new Produto(20, "Teclado mecânico", 349.00));
        arq.gravar(produtos);

        arq.acrescentar(new Produto(30, "Monitor 24 polegadas", 899.50));

        System.out.println("Conteúdo de produtos.ser:");
        for (Produto p : arq.ler()) {
            System.out.println(p);
        }
        // Compare com os 92 bytes do Exemplo 3: a serialização grava também
        // o nome da classe, dos atributos e seus tipos
        System.out.println("Tamanho do arquivo: " + new File("produtos.ser").length() + " bytes");
    }
}
