import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI {
    private JFrame frm ;
    private JButton[][] btn;
    private char player_xyz;
    private char[][] base;
    JLabel textfield = new JLabel();

    public TicTacToeGUI() {
      frm= new JFrame("Tic Tac Toe");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(400, 400);
        frm.setLayout(new GridLayout(3, 3));
        textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);

        btn = new JButton[3][3];
        base = new char[3][3];
        player_xyz = 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btn[i][j] = new JButton("");
                btn[i][j].setFont(new Font("cooper black", Font.PLAIN, 60));
                btn[i][j].setFocusPainted(false);
                btn[i][j].addActionListener(new ButtonClickListener(i, j));
                frm.add(btn[i][j]);
                base[i][j] = ' ';
            }
        }

        frm.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int column;

        public ButtonClickListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (btn[row][column].getText().equals("") && !gameOver()) {
                btn[row][column].setText(String.valueOf(player_xyz));
                base[row][column] = player_xyz;
                if (hasWon(player_xyz)) {
                    JOptionPane.showMessageDialog(frm, "Player " + player_xyz + " has won!");
                } else if (baseFull()) {
                    JOptionPane.showMessageDialog(frm, "The game is a draw!");
                } else {
                    player_xyz = (player_xyz == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (base[i][0] == player && base[i][1] == player && base[i][2] == player) {
                return true;
            }
            if (base[0][i] == player && base[1][i] == player && base[2][i] == player) {
                return true;
            }
        }
        if (base[0][0] == player && base[1][1] == player && base[2][2] == player) {
            return true;
        }
        if (base[0][2] == player && base[1][1] == player && base[2][0] == player) {
            return true;
        }
        return false;
    }

    private boolean baseFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (base[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean gameOver() {
        return hasWon('X') || hasWon('O') || baseFull();
    }

    public static void main(String[] args) {
        TicTacToeGUI t =new TicTacToeGUI();
    }
}