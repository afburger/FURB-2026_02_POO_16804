package exemplos.unidade07.jogos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CAMADA DE PERSISTÊNCIA
 *
 * Grava e lê a lista de jogos num arquivo texto, um jogo por linha,
 * com os campos separados por ponto e vírgula:
 *
 *   Hollow Knight;PC;42
 *   Mario Kart 8;Switch;15
 *
 * Esta classe não sabe nada de tela: ela não mostra mensagem nenhuma.
 * Quando algo dá errado, ela lança a exceção e quem chamou (a tela)
 * decide como avisar o usuário.
 */
public class ArquivoJogos {

    private static final String SEPARADOR = ";";

    private File arquivo;

    public ArquivoJogos(String caminho) {
        this.arquivo = new File(caminho);
    }

    /**
     * Grava a lista inteira, substituindo o conteúdo anterior do arquivo.
     * Como a tela mantém a lista completa em memória, gravar tudo de novo
     * é mais simples do que acrescentar linha a linha.
     */
    public void gravar(List<Jogo> jogos) throws IOException {
        // try-with-resources: o PrintWriter é fechado automaticamente,
        // mesmo que aconteça uma exceção no meio da gravação
        try (PrintWriter out = new PrintWriter(arquivo, "UTF-8")) {
            for (Jogo jogo : jogos) {
                out.println(jogo.getTitulo() + SEPARADOR
                        + jogo.getPlataforma() + SEPARADOR
                        + jogo.getHorasJogadas());
            }
        }
    }

    /**
     * Lê todos os jogos do arquivo.
     * Se o arquivo ainda não existe (primeira execução), devolve uma lista vazia.
     */
    public List<Jogo> ler() throws IOException {
        List<Jogo> jogos = new ArrayList<>();

        if (!arquivo.exists()) {
            return jogos;
        }

        try (Scanner sc = new Scanner(arquivo, "UTF-8")) {
            int numeroLinha = 0;
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                numeroLinha++;

                if (linha.isBlank()) {
                    continue; // ignora linhas em branco
                }

                try {
                    String[] campos = linha.split(SEPARADOR);
                    String titulo = campos[0];
                    String plataforma = campos[1];
                    int horas = Integer.parseInt(campos[2].trim());
                    jogos.add(new Jogo(titulo, plataforma, horas));
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    // Linha faltando campo, horas que não são número ou dado inválido
                    // (NumberFormatException é filha de IllegalArgumentException).
                    // Converte para IOException para a tela tratar tudo num lugar só.
                    throw new IOException("Linha " + numeroLinha + " do arquivo está inválida: " + linha, e);
                }
            }
        }
        return jogos;
    }

    /** Caminho completo do arquivo, útil para mostrar ao usuário onde ele foi salvo. */
    public String getCaminho() {
        return arquivo.getAbsolutePath();
    }

    /*
     * Pergunta para a turma: o que acontece se o usuário cadastrar um jogo
     * cujo título tem um ponto e vírgula, como "Jogo; Edição Especial"?
     * Grave, carregue e veja. Como você resolveria?
     */
}
