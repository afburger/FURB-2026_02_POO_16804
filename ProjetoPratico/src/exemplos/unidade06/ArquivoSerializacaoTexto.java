package exemplos.unidade06;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Exemplo 6: SERIALIZAÇÃO EM TEXTO de objetos, no formato XML.
 *
 * Gravação: FileOutputStream -> BufferedOutputStream -> XMLEncoder
 * Leitura:  FileInputStream  -> BufferedInputStream  -> XMLDecoder
 *
 * Mesma ideia do Exemplo 5 (gravar e reconstruir o objeto inteiro),
 * mas o resultado é um arquivo TEXTO, que pode ser aberto no bloco de notas.
 *
 * Diferença para o Exemplo 1: lá nós mesmos decidimos o formato da linha
 * (codigo;descricao;preco) e fizemos a conversão. Aqui o XMLEncoder faz a
 * conversão sozinho, a partir dos getters e setters da classe.
 *
 * Requisitos do XMLEncoder (padrão JavaBean): classe pública, construtor
 * público sem parâmetros e getters/setters para os atributos.
 *
 * Atenção: só use o XMLDecoder com arquivos de origem confiável, porque o
 * XML pode mandar criar objetos e chamar métodos quaisquer.
 */
public class ArquivoSerializacaoTexto {

    private final File arquivo;

    public ArquivoSerializacaoTexto(String caminho) {
        this.arquivo = new File(caminho);
    }

    /**
     * CRIAÇÃO: serializa a lista de produtos em XML, recriando o arquivo.
     */
    public void gravar(List<Produto> produtos) throws FileNotFoundException {
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo)))) {
            encoder.writeObject(produtos);
        }
    }

    /**
     * ALTERAÇÃO: acrescenta um produto.
     * O XML é um documento único (abre e fecha tags), então não dá para
     * simplesmente acrescentar no final: lemos, alteramos e gravamos de novo.
     */
    public void acrescentar(Produto p) throws FileNotFoundException {
        List<Produto> produtos = arquivo.exists() ? ler() : new ArrayList<>();
        produtos.add(p);
        gravar(produtos);
    }

    /**
     * LEITURA: desserializa a lista de produtos a partir do XML.
     */
    @SuppressWarnings("unchecked")
    public List<Produto> ler() throws FileNotFoundException {
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(arquivo)))) {
            return (List<Produto>) decoder.readObject();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArquivoSerializacaoTexto arq = new ArquivoSerializacaoTexto("produtos.xml");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(10, "Mouse sem fio", 89.90));
        produtos.add(new Produto(20, "Teclado mecânico", 349.00));
        arq.gravar(produtos);

        arq.acrescentar(new Produto(30, "Monitor 24 polegadas", 899.50));

        System.out.println("Objetos lidos de produtos.xml:");
        for (Produto p : arq.ler()) {
            System.out.println(p);
        }

        // Mostra o arquivo como texto, para ver o XML gerado
        System.out.println("\nConteúdo do arquivo produtos.xml:");
        try (Scanner sc = new Scanner(new File("produtos.xml"))) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }
}
