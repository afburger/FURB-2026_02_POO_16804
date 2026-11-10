package exemplos.unidade07.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Contador de público de um evento, com lotação máxima.
 * Exemplo de BorderLayout e de ActionListener em botões.
 *
 * O BorderLayout divide a janela em 5 regiões:
 *
 *   +----------------------------------+
 *   |              NORTH               |
 *   +--------+---------------+---------+
 *   |  WEST  |    CENTER     |  EAST   |
 *   +--------+---------------+---------+
 *   |              SOUTH               |
 *   +----------------------------------+
 *
 * Regras importantes:
 *  - Cada região guarda UM componente só. Para colocar vários, coloque um
 *    JPanel na região e adicione os componentes dentro do JPanel (veja o SOUTH).
 *  - O CENTER fica com todo o espaço que sobra quando a janela é redimensionada.
 *  - O JFrame já usa BorderLayout por padrão. Aqui o setLayout é chamado
 *    para deixar isso explícito e para definir o espaçamento entre as regiões.
 */
public class ContadorPublicoUi extends JFrame {

    private static final int LOTACAO_MAXIMA = 10;

    // Estado da tela
    private int pessoas = 0;

    // Os componentes viram atributos da tela (igual ao exemplo SaudacaoUi dos slides)
    private JLabel lblTitulo = new JLabel("Show da Banda Imaginária", SwingConstants.CENTER);
    private JLabel lblContador = new JLabel("0", SwingConstants.CENTER);
    private JButton btnSaiu = new JButton("- Saiu");
    private JButton btnEntrou = new JButton("+ Entrou");
    private JButton btnZerar = new JButton("Zerar");
    private JLabel lblStatus = new JLabel();

    public ContadorPublicoUi() {
        super("Contador de público");

        // 10 pixels de espaço horizontal e vertical entre as regiões
        setLayout(new BorderLayout(10, 10));

        // Aparência dos rótulos
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblContador.setFont(new Font("SansSerif", Font.BOLD, 64));

        // ---------- Montagem das regiões ----------
        add(lblTitulo, BorderLayout.NORTH);
        add(btnSaiu, BorderLayout.WEST);
        add(lblContador, BorderLayout.CENTER);
        add(btnEntrou, BorderLayout.EAST);

        // A região SOUTH aceita um componente só, então usamos um JPanel
        // (que por padrão usa FlowLayout) para agrupar o botão e o rótulo
        JPanel painelSul = new JPanel(new FlowLayout());
        painelSul.add(btnZerar);
        painelSul.add(lblStatus);
        add(painelSul, BorderLayout.SOUTH);

        // Margem interna para os componentes não encostarem na borda da janela
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------- Registro dos ouvintes (listeners) ----------
        // Três formas de registrar um ActionListener. O resultado é o mesmo:
        // a cada clique, o botão chama o actionPerformed do ouvinte registrado.

        // Forma 1: classe anônima
        btnEntrou.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entrou();
            }
        });

        // Forma 2: expressão lambda (ActionListener tem um único método)
        btnSaiu.addActionListener(e -> saiu());

        // Forma 3: objeto de uma classe interna com nome (definida no final do arquivo)
        btnZerar.addActionListener(new ZerarListener());

        atualizarTela();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null); // centraliza a janela na tela
    }

    private void entrou() {
        pessoas++;
        atualizarTela();
    }

    private void saiu() {
        pessoas--;
        atualizarTela();
    }

    /**
     * Toda alteração de estado passa por aqui. Assim a tela nunca fica
     * desatualizada em relação ao atributo "pessoas".
     */
    private void atualizarTela() {
        lblContador.setText(String.valueOf(pessoas));

        // Os botões se habilitam ou desabilitam conforme o estado,
        // então o usuário nem consegue passar do limite ou ficar negativo
        btnEntrou.setEnabled(pessoas < LOTACAO_MAXIMA);
        btnSaiu.setEnabled(pessoas > 0);
        btnZerar.setEnabled(pessoas > 0);

        if (pessoas == LOTACAO_MAXIMA) {
            lblContador.setForeground(Color.RED);
            lblStatus.setText("LOTADO!");
        } else {
            lblContador.setForeground(Color.BLACK);
            lblStatus.setText("Lugares livres: " + (LOTACAO_MAXIMA - pessoas));
        }
    }

    /**
     * Classe interna (inner class): como está DENTRO de ContadorPublicoUi,
     * ela enxerga os atributos e métodos da tela, inclusive os privados.
     * Use esta forma quando o tratamento do evento for maior ou reaproveitado.
     */
    private class ZerarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // ContadorPublicoUi.this = o objeto da tela que "contém" este ouvinte.
            // Só "this" aqui seria o próprio ZerarListener, que não é uma janela.
            int resposta = JOptionPane.showConfirmDialog(ContadorPublicoUi.this,
                    "Zerar a contagem de público?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                pessoas = 0;
                atualizarTela();
            }
        }
    }

    public static void main(String[] args) {
        new ContadorPublicoUi().setVisible(true);
    }
}
