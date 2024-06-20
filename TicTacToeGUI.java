import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Game implements ActionListener {

    private Random random = new Random();
    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel textField = new JLabel();
    private JButton[] buttons = new JButton[9];
    private JButton replayButton = new JButton("Replay");
    private boolean player1Turn;

    Game() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        replayButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        replayButton.setFocusable(false);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        replayButton.setVisible(false); // Initially hide replay button

        JPanel replayPanel = new JPanel();
        replayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        replayPanel.setPreferredSize(new Dimension(800, 100));
        replayPanel.setBackground(new Color(50, 50, 50));
        replayPanel.add(replayButton);
        frame.add(replayPanel, BorderLayout.SOUTH);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1Turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    private void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        } else {
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    private void check() {
        // Check win conditions for X and O
        if (checkWinCondition("X")) {
            endGame("X wins!");
        } else if (checkWinCondition("O")) {
            endGame("O wins!");
        } else {
            // Check for draw condition
            boolean draw = true;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().isEmpty()) {
                    draw = false;
                    break;
                }
            }
            if (draw) {
                endGame("It's a draw!");
            }
        }
    }

    private boolean checkWinCondition(String player) {
        // Check all possible winning combinations
        return (buttons[0].getText().equals(player) && buttons[1].getText().equals(player) && buttons[2].getText().equals(player))
                || (buttons[3].getText().equals(player) && buttons[4].getText().equals(player) && buttons[5].getText().equals(player))
                || (buttons[6].getText().equals(player) && buttons[7].getText().equals(player) && buttons[8].getText().equals(player))
                || (buttons[0].getText().equals(player) && buttons[3].getText().equals(player) && buttons[6].getText().equals(player))
                || (buttons[1].getText().equals(player) && buttons[4].getText().equals(player) && buttons[7].getText().equals(player))
                || (buttons[2].getText().equals(player) && buttons[5].getText().equals(player) && buttons[8].getText().equals(player))
                || (buttons[0].getText().equals(player) && buttons[4].getText().equals(player) && buttons[8].getText().equals(player))
                || (buttons[2].getText().equals(player) && buttons[4].getText().equals(player) && buttons[6].getText().equals(player));
    }

    private void endGame(String message) {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText(message);
        replayButton.setVisible(true); // Show replay button when game ends
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setForeground(null);
            buttons[i].setBackground(null);
        }
        replayButton.setVisible(false); // Hide replay button again
        firstTurn(); // Start a new game
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });
    }
}
