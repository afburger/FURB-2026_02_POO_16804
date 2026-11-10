package exemplos.unidade07.gui;

import javax.swing.JOptionPane;

/**
 * Exemplos das caixas de diálogo prontas do JOptionPane.
 *
 * O JOptionPane é o jeito mais rápido de conversar com o usuário sem montar
 * uma janela inteira: basta chamar um método estático e a caixa aparece.
 *
 * Todas essas caixas são MODAIS: o programa fica parado naquela linha até o
 * usuário fechar a caixa. Por isso dá para usar o retorno logo na linha seguinte.
 *
 * Os quatro métodos principais:
 *   showMessageDialog -> só mostra uma mensagem (retorno void)
 *   showInputDialog   -> pede um valor ao usuário (retorna String, ou null se cancelar)
 *   showConfirmDialog -> pergunta Sim / Não / Cancelar (retorna um int)
 *   showOptionDialog  -> botões personalizados (retorna o índice do botão clicado)
 *
 * O primeiro parâmetro (parentComponent) é a janela "dona" da caixa. Com null,
 * a caixa aparece centralizada na tela. Dentro de um JFrame, passe "this".
 */
public class ExemplosJOptionPane {

    public static void main(String[] args) {
        // O próprio menu já é um exemplo de showOptionDialog:
        // cada posição do array vira um botão, e o retorno é o índice clicado.
        String[] exemplos = {"Mensagens", "Texto", "Número", "Confirmação", "Lista", "Sair"};
        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(
                    null,                              // janela dona
                    "Qual exemplo você quer ver?",     // mensagem
                    "Exemplos de JOptionPane",         // título da caixa
                    JOptionPane.DEFAULT_OPTION,        // tipo de opção (ignorado, pois os botões são nossos)
                    JOptionPane.QUESTION_MESSAGE,      // ícone
                    null,                              // ícone personalizado (null = usa o padrão)
                    exemplos,                          // textos dos botões
                    exemplos[0]);                      // botão com o foco inicial

            switch (escolha) {
                case 0:
                    exemploMensagens();
                    break;
                case 1:
                    exemploEntradaTexto();
                    break;
                case 2:
                    exemploEntradaNumerica();
                    break;
                case 3:
                    exemploConfirmacao();
                    break;
                case 4:
                    exemploListaDeOpcoes();
                    break;
            }
            // CLOSED_OPTION (-1) significa que o usuário fechou a caixa no X
        } while (escolha != 5 && escolha != JOptionPane.CLOSED_OPTION);
    }

    /**
     * showMessageDialog: o mesmo método com tipos de mensagem diferentes.
     * O tipo muda só o ícone, o comportamento é igual.
     */
    private static void exemploMensagens() {
        // Versão mais curta: janela dona e mensagem (tipo INFORMATION_MESSAGE por padrão)
        JOptionPane.showMessageDialog(null, "Mensagem simples, com título e ícone padrão.");

        // Versão completa: janela dona, mensagem, título e tipo
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!",
                "Informação", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "A bateria está acabando.",
                "Aviso", JOptionPane.WARNING_MESSAGE);

        JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo.",
                "Erro", JOptionPane.ERROR_MESSAGE);

        // O \n quebra a linha dentro da caixa
        JOptionPane.showMessageDialog(null, "Linha 1\nLinha 2\nLinha 3",
                "Sem ícone", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * showInputDialog: sempre devolve uma String.
     * Se o usuário clicar em Cancelar ou fechar no X, devolve null.
     */
    private static void exemploEntradaTexto() {
        String nome = JOptionPane.showInputDialog("Qual é o seu nome?");

        // Sempre trate o null ANTES de chamar qualquer método da String,
        // senão o cancelamento vira NullPointerException.
        if (nome == null) {
            JOptionPane.showMessageDialog(null, "Você cancelou.");
            return;
        }
        if (nome.isBlank()) {
            JOptionPane.showMessageDialog(null, "Você não digitou nada.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Com valor inicial: o campo já vem preenchido
        String cidade = JOptionPane.showInputDialog("Em que cidade você mora?", "Blumenau");

        JOptionPane.showMessageDialog(null, "Olá, " + nome + "!\nCidade: " + cidade);
    }

    /**
     * Para números, o showInputDialog continua devolvendo String:
     * a conversão é por nossa conta, e ela pode lançar NumberFormatException.
     */
    private static void exemploEntradaNumerica() {
        Double nota1 = lerNota("Digite a nota da P1:");
        if (nota1 == null) {
            return; // cancelou
        }
        Double nota2 = lerNota("Digite a nota da P2:");
        if (nota2 == null) {
            return;
        }

        double media = (nota1 + nota2) / 2;
        String situacao = media >= 6 ? "Aprovado" : "Reprovado";

        JOptionPane.showMessageDialog(null,
                String.format("Média: %.2f%nSituação: %s", media, situacao),
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Fica pedindo a nota até o usuário digitar um número válido entre 0 e 10.
     * Retorna null se o usuário cancelar.
     */
    private static Double lerNota(String mensagem) {
        while (true) {
            String texto = JOptionPane.showInputDialog(mensagem);
            if (texto == null) {
                return null;
            }
            try {
                // Aceita vírgula, que é o que o usuário brasileiro digita
                double nota = Double.parseDouble(texto.trim().replace(",", "."));
                if (nota < 0 || nota > 10) {
                    JOptionPane.showMessageDialog(null, "A nota deve estar entre 0 e 10.",
                            "Valor inválido", JOptionPane.WARNING_MESSAGE);
                } else {
                    return nota;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "\"" + texto + "\" não é um número.",
                        "Valor inválido", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * showConfirmDialog: devolve um int que deve ser comparado com as
     * constantes YES_OPTION, NO_OPTION, CANCEL_OPTION e CLOSED_OPTION.
     */
    private static void exemploConfirmacao() {
        // Sim / Não
        int resposta = JOptionPane.showConfirmDialog(null, "Você gosta de café?",
                "Pergunta", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Boa escolha!");
        } else if (resposta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Mais café para os outros, então.");
        } else {
            JOptionPane.showMessageDialog(null, "Você fechou a caixa sem responder.");
        }

        // Sim / Não / Cancelar, com ícone de aviso (caso clássico do "salvar antes de sair?")
        resposta = JOptionPane.showConfirmDialog(null,
                "Deseja salvar as alterações antes de sair?",
                "Sair", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        switch (resposta) {
            case JOptionPane.YES_OPTION:
                JOptionPane.showMessageDialog(null, "Salvando e saindo...");
                break;
            case JOptionPane.NO_OPTION:
                JOptionPane.showMessageDialog(null, "Saindo sem salvar...");
                break;
            default: // CANCEL_OPTION ou CLOSED_OPTION
                JOptionPane.showMessageDialog(null, "Operação cancelada, continua tudo aberto.");
        }
    }

    /**
     * showInputDialog com um array de opções: em vez de um campo de texto,
     * aparece uma caixa de seleção (combo). O retorno é Object, então precisa de cast.
     */
    private static void exemploListaDeOpcoes() {
        String[] linguagens = {"Java", "Python", "C", "JavaScript", "Kotlin"};

        Object selecionada = JOptionPane.showInputDialog(
                null,
                "Qual a sua linguagem favorita?",
                "Lista de opções",
                JOptionPane.QUESTION_MESSAGE,
                null,          // ícone personalizado
                linguagens,    // opções da lista
                linguagens[0]); // opção selecionada inicialmente

        if (selecionada == null) {
            JOptionPane.showMessageDialog(null, "Você cancelou.");
        } else {
            String linguagem = (String) selecionada;
            JOptionPane.showMessageDialog(null, "Você escolheu " + linguagem + ".");
        }
    }
}
