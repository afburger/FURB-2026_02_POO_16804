package exemplos.unidade07.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ExemploGui extends JFrame {

    private JTextField tfNome = new JTextField();
    private JButton btClicar = new JButton("Clicar");

    public static void main(String[] args) {
        new ExemploGui();
    }

    public ExemploGui() {
        super("Título");
        setLayout(new BorderLayout(10, 10));


        add(new JLabel("Nome:"), BorderLayout.WEST);
        add(tfNome, BorderLayout. CENTER);
        add(btClicar, BorderLayout.EAST);

        // Essa linha, tem o mesmo comportamento das 6 linhas abaixo.
        btClicar.addActionListener(e -> exeutaClique());

        // btClicar.addActionListener(new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent e) {
                //exeutaClique();
            //}
        //});

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 240);
        setVisible(true);
    }

    private void exeutaClique() {
        new JOptionPane().showMessageDialog(null, "Olá " + tfNome.getText());
    }
}
