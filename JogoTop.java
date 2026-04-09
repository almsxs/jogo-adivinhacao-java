package src;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

 

public class JogoTop {
    private int numeroSecreto;
    private int tentativas;

    private JLabel mensagem;
    private JTextField campo;
    private JFrame frame;

    public JogoTop() {
        iniciarJogo();
        criarInterface();
    }

    private void iniciarJogo() {
        Random random = new Random();
        numeroSecreto = random.nextInt(100) + 1;
        tentativas = 0;
    }

    private void criarInterface() {
        frame = new JFrame("🎮 Jogo Adivinhação PRO");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(20, 20, 20));

        JLabel titulo = new JLabel("Adivinhe o número (1-100)");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        campo = new JTextField();
        campo.setMaximumSize(new Dimension(200, 30));
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botao = new JButton("TENTAR");
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(200, 35));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(50, 150, 255));
        botao.setForeground(Color.WHITE);

        JButton reset = new JButton("REINICIAR");
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setMaximumSize(new Dimension(200, 35));
        reset.setFont(new Font("Arial", Font.BOLD, 14));
        reset.setBackground(new Color(255, 100, 100));
        reset.setForeground(Color.WHITE);

        mensagem = new JLabel(" ");
        mensagem.setForeground(Color.WHITE);
        mensagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensagem.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel contador = new JLabel("Tentativas: 0");
        contador.setForeground(Color.LIGHT_GRAY);
        contador.setAlignmentX(Component.CENTER_ALIGNMENT);
        contador.setFont(new Font("Arial", Font.PLAIN, 14));

        botao.addActionListener(e -> {
            try {
                String texto = campo.getText().trim();
                if (texto.isEmpty()) {
                    mensagem.setText("Digite um número!");
                    return;
                }

                int tentativa = Integer.parseInt(texto);
                tentativas++;
                contador.setText("Tentativas: " + tentativas);
                campo.setText(""); // Limpa o campo após tentativa

                if (tentativa < numeroSecreto) {
                    mensagem.setText("🔼 Muito baixo!");
                    painel.setBackground(new Color(40, 60, 120));
                } else if (tentativa > numeroSecreto) {
                    mensagem.setText("🔽 Muito alto!");
                    painel.setBackground(new Color(120, 60, 40));
                } else {
                    mensagem.setText("🎉 ACERTOU em " + tentativas + " tentativas!");
                    painel.setBackground(new Color(40, 120, 60));
                    botao.setEnabled(false); // Desabilita botão após vitória
                }

            } catch (NumberFormatException ex) {
                mensagem.setText("❌ Digite um número válido!");
                painel.setBackground(new Color(120, 40, 40));
            }
        });

        reset.addActionListener(e -> {
            iniciarJogo();
            mensagem.setText("🎮 Novo jogo iniciado!");
            contador.setText("Tentativas: 0");
            painel.setBackground(new Color(20, 20, 20));
            campo.setText("");
            botao.setEnabled(true); // Reabilita botão
        });

        // Adiciona componentes com espaçamentos
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 25)));
        painel.add(campo);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(botao);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(reset);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(mensagem);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(contador);

        frame.add(painel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoTop();
        });
    }
}
