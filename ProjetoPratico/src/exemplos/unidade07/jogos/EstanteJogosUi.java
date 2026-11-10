package exemplos.unidade07.jogos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * CAMADA DE APRESENTAÇÃO
 *
 * Tela da estante de jogos: cadastra jogos, salva a lista num arquivo texto
 * e recupera a lista do arquivo.
 *
 * Organização da tela (BorderLayout do JFrame):
 *   NORTH  -> painel com os campos (GridLayout de 3 linhas e 2 colunas)
 *   CENTER -> área de texto com a lista de jogos (dentro de um JScrollPane)
 *   SOUTH  -> painel com os botões (FlowLayout)
 *
 * A tela só cuida de ler os campos, chamar as outras camadas e mostrar o
 * resultado. As regras ficam em Jogo e o arquivo fica em ArquivoJogos.
 */
public class EstanteJogosUi extends JFrame {

    // Componentes
    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtPlataforma = new JTextField(20);
    private JTextField txtHoras = new JTextField(20);
    private JTextArea txaLista = new JTextArea(10, 35);
    private JButton btnAdicionar = new JButton("Adicionar");
    private JButton btnSalvar = new JButton("Salvar no arquivo");
    private JButton btnCarregar = new JButton("Carregar do arquivo");

    // Dados em memória e acesso ao arquivo
    private List<Jogo> jogos = new ArrayList<>();
    private ArquivoJogos arquivo = new ArquivoJogos("jogos.txt");

    public EstanteJogosUi() {
        super("Minha estante de jogos");

        // ---------- NORTH: campos do formulário ----------
        // GridLayout(linhas, colunas, espaço horizontal, espaço vertical):
        // os componentes vão preenchendo a grade da esquerda para a direita
        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 5, 5));
        painelCampos.add(new JLabel("Título:"));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel("Plataforma:"));
        painelCampos.add(txtPlataforma);
        painelCampos.add(new JLabel("Horas jogadas:"));
        painelCampos.add(txtHoras);

        // ---------- CENTER: lista de jogos ----------
        // JTextArea é um campo de texto com várias linhas.
        // O JScrollPane adiciona barra de rolagem quando o conteúdo não cabe.
        txaLista.setEditable(false);

        // ---------- SOUTH: botões ----------
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCarregar);

        setLayout(new BorderLayout(10, 10));
        add(painelCampos, BorderLayout.NORTH);
        add(new JScrollPane(txaLista), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------- Ouvintes ----------
        btnAdicionar.addActionListener(e -> adicionar());
        btnSalvar.addActionListener(e -> salvar());
        btnCarregar.addActionListener(e -> carregar());

        atualizarLista();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // ajusta o tamanho da janela ao tamanho preferido dos componentes
        setLocationRelativeTo(null);
    }

    /** Lê os campos, cria o Jogo e adiciona na lista em memória (ainda não grava no arquivo). */
    private void adicionar() {
        try {
            int horas = Integer.parseInt(txtHoras.getText().trim());
            Jogo jogo = new Jogo(txtTitulo.getText(), txtPlataforma.getText(), horas);

            jogos.add(jogo);
            atualizarLista();
            limparCampos();

        } catch (NumberFormatException e) {
            // ATENÇÃO à ordem dos catch: NumberFormatException é FILHA de
            // IllegalArgumentException. Se o catch da mãe viesse primeiro,
            // este aqui nunca seria alcançado (e o código nem compilaria).
            JOptionPane.showMessageDialog(this, "Horas jogadas deve ser um número inteiro.",
                    "Dado inválido", JOptionPane.WARNING_MESSAGE);
            txtHoras.requestFocus();

        } catch (IllegalArgumentException e) {
            // Mensagens das validações da classe Jogo
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Dado inválido", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Grava a lista em memória no arquivo. */
    private void salvar() {
        try {
            arquivo.gravar(jogos);
            JOptionPane.showMessageDialog(this,
                    jogos.size() + " jogo(s) salvo(s) em:\n" + arquivo.getCaminho(),
                    "Arquivo salvo", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Recupera a lista do arquivo, substituindo a lista em memória. */
    private void carregar() {
        // Se já tem algo na tela, confirma antes de substituir
        if (!jogos.isEmpty()) {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Carregar do arquivo substitui a lista atual.\nO que não foi salvo será perdido. Continuar?",
                    "Carregar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            jogos = arquivo.ler();
            atualizarLista();
            JOptionPane.showMessageDialog(this, jogos.size() + " jogo(s) carregado(s) do arquivo.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Reescreve a área de texto a partir da lista em memória. */
    private void atualizarLista() {
        if (jogos.isEmpty()) {
            txaLista.setText("(nenhum jogo na estante)");
            return;
        }

        txaLista.setText("");
        int total = 0;
        for (Jogo jogo : jogos) {
            txaLista.append(jogo + "\n"); // usa o toString() de Jogo
            total += jogo.getHorasJogadas();
        }
        txaLista.append("\nTotal: " + jogos.size() + " jogo(s), " + total + "h jogadas");
    }

    private void limparCampos() {
        txtTitulo.setText("");
        txtPlataforma.setText("");
        txtHoras.setText("");
        txtTitulo.requestFocus(); // volta o cursor para o primeiro campo
    }

    public static void main(String[] args) {
        new EstanteJogosUi().setVisible(true);
    }
}
