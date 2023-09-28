package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private Board board;
    private char currentPlayer;
    private String playerXName;
    private String playerOName;

    public TicTacToeGUI(int size) {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(size, size));

        buttons = new JButton[size][size];
        board = new Board(size);
        playerXName = getPlayerName("Player X");
        playerOName = getPlayerName("Player O");
        currentPlayer = 'X';

        initializeButtons();
        frame.setVisible(true);
    }

    private String getPlayerName(String playerLabel) {
        return JOptionPane.showInputDialog(frame, "Enter " + playerLabel + " name:");
    }

    private void initializeButtons() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);

                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[r][c].getText().equals("") && currentPlayer != ' ') {
                            buttons[r][c].setText(String.valueOf(currentPlayer));
                            buttons[r][c].setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);
                            Square square = board.getSquare(r, c);
                            square.setValue(currentPlayer);
                            if (checkWin(square)) {
                                int option = JOptionPane.showConfirmDialog(frame, playerXName + " wins! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    resetBoard();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Thank you for playing the game!");
                                    System.exit(0);
                                }
                            } else if (countOccupiedSquares() == board.getSize() * board.getSize()) {
                                int option = JOptionPane.showConfirmDialog(frame, "It's a draw! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    resetBoard();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Thank you for playing the game! from Nishant!");
                                    System.exit(0);
                                }
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                showMessage(playerXName + "'s turn");
                            }
                        }
                    }
                });

                frame.add(buttons[row][col]);
            }
        }
        showMessage(playerXName + "'s turn");
    }

    private boolean checkWin(Square square) {
        int row = square.getRow();
        int col = square.getCol();
        char symbol = square.getValue();

        // Check row
        boolean rowWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getSquare(row, i).getValue() != symbol) {
                rowWin = false;
                break;
            }
        }

        // Check column
        boolean colWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getSquare(i, col).getValue() != symbol) {
                colWin = false;
                break;
            }
        }

        // Check diagonals
        boolean diag1Win = true;
        boolean diag2Win = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getSquare(i, i).getValue() != symbol) {
                diag1Win = false;
            }
            if (board.getSquare(i, board.getSize() - 1 - i).getValue() != symbol) {
                diag2Win = false;
            }
        }

        return rowWin || colWin || diag1Win || diag2Win;
    }

    private int countOccupiedSquares() {
        int count = 0;
        for (Square square : board.getAllSquares()) {
            if (square.getValue() != '-') {
                count++;
            }
        }
        return count;
    }

    private void resetBoard() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                buttons[row][col].setText("");
                buttons[row][col].setForeground(Color.BLACK);
                board.getSquare(row, col).setValue('-');
            }
        }
        currentPlayer = 'X';
        showMessage(playerXName + "'s turn");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
