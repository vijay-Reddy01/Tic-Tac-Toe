import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class TicTacToeGame implements ActionListener {

     Random rnd = new Random();
    private JFrame frm = new JFrame();
    private JPanel titlePnl = new JPanel();
    private JPanel btnPnl = new JPanel();
    private JLabel textField = new JLabel();
    private JButton[]btn = new JButton[9];
    private JButton replayBtn = new JButton("Replay");
    private boolean player1Turn;

    TicTacToeGame() {
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(800, 800);
        frm.getContentPane().setBackground(new Color(150, 150, 150));
        frm.setLayout(new BorderLayout());
        frm.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        titlePnl.setLayout(new BorderLayout());
        titlePnl.setPreferredSize(new Dimension(800, 100));

        btnPnl.setLayout(new GridLayout(3, 3));
        btnPnl.setBackground(new Color(150, 150, 150));

        for (int a = 0; a < 9; a++) {
           btn[a] = new JButton();
            btnPnl.add(btn[a]);
           btn[a].setFont(new Font("MV Boli", Font.BOLD, 120));
           btn[a].setFocusable(false);
           btn[a].addActionListener(this);
        }

        titlePnl.add(textField);
        frm.add(titlePnl, BorderLayout.NORTH);
        frm.add(btnPnl, BorderLayout.CENTER);

        replayBtn.setFont(new Font("MV Boli", Font.BOLD, 30));
        replayBtn.setFocusable(false);
        replayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTicTacToeGame();
            }
        });
        replayBtn.setVisible(false);

        JPanel replayPanel = new JPanel();
        replayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        replayPanel.setPreferredSize(new Dimension(800, 100));
        replayPanel.setBackground(new Color(150, 150, 150));
        replayPanel.add(replayBtn);
        frm.add(replayPanel, BorderLayout.SOUTH);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int a = 0; a < 9; a++) {
            if (e.getSource() ==btn[a]) {
                if (player1Turn) {
                    if (btn[a].getText().isEmpty()) {
                       btn[a].setForeground(new Color(255, 0, 0));
                       btn[a].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        check();
                    }
                } else {
                    if (btn[a].getText().isEmpty()) {
                       btn[a].setForeground(new Color(0, 0, 255));
                       btn[a].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    private void firstTurn() {
        if (rnd.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        } else {
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    private void check() {
        if (winCond("X")) {
            endGame("X wins!");
        } else if (winCond("O")) {
            endGame("O wins!");
        } else {
            boolean draw = true;
            for (int a = 0; a < 9; a++) {
                if (btn[a].getText().isEmpty()) {
                    draw = false;
                    break;
                }
            }
            if (draw) {
                endGame("It's a draw!");
            }
        }
    }

    private boolean winCond(String player) {
        return (btn[0].getText().equals(player) &&btn[1].getText().equals(player) &&btn[2].getText().equals(player))
                || (btn[3].getText().equals(player) &&btn[4].getText().equals(player) &&btn[5].getText().equals(player))
                || (btn[6].getText().equals(player) &&btn[7].getText().equals(player) &&btn[8].getText().equals(player))
                || (btn[0].getText().equals(player) &&btn[3].getText().equals(player) &&btn[6].getText().equals(player))
                || (btn[1].getText().equals(player) &&btn[4].getText().equals(player) &&btn[7].getText().equals(player))
                || (btn[2].getText().equals(player) &&btn[5].getText().equals(player) &&btn[8].getText().equals(player))
                || (btn[0].getText().equals(player) &&btn[4].getText().equals(player) &&btn[8].getText().equals(player))
                || (btn[2].getText().equals(player) &&btn[4].getText().equals(player) &&btn[6].getText().equals(player));
    }

    private void endGame(String message) {
        for (int a = 0; a < 9; a++) {
           btn[a].setEnabled(false);
        }
        textField.setText(message);
        replayBtn.setVisible(true);
    }

    private void resetTicTacToeGame() {
        for (int a = 0; a < 9; a++) {
           btn[a].setText("");
           btn[a].setEnabled(true);
           btn[a].setForeground(null);
           btn[a].setBackground(null);
        }
        replayBtn.setVisible(false); 
        firstTurn();
}

    public static void main(String[] args){
        TicTacToeGame t=new TicTacToeGame();
    }
}

