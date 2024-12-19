package tictactoegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons = new JButton[3][3]; // Matriz para los botones del tablero
    private boolean xTurn = true; // Indica si es el turno de 'X'

    public TicTacToe() {
        setTitle("Tic Tac Toe"); // Título de la ventana
        setSize(400, 400); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setLayout(new GridLayout(3, 3)); // Diseño de la cuadrícula 3x3

        // Crear los botones del tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60)); // Fuente grande para los símbolos
                buttons[i][j].setFocusPainted(false); // Quitar el borde de enfoque
                buttons[i][j].addActionListener(new ButtonClickListener(i, j)); // Agregar listener a cada botón
                add(buttons[i][j]); // Agregar botón a la ventana
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col; // Posición del botón en la cuadrícula

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Si el botón ya tiene un símbolo, no hacer nada
            if (!buttons[row][col].getText().isEmpty()) {
                return;
            }

            // Establecer el símbolo según el turno
            if (xTurn) {
                buttons[row][col].setText("X");
            } else {
                buttons[row][col].setText("O");
            }

            // Cambiar el turno
            xTurn = !xTurn;

            // Verificar si hay un ganador o empate
            if (checkWinner()) {
                JOptionPane.showMessageDialog(null, "¡Ganador: " + (xTurn ? "O" : "X") + "!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "¡Empate!");
                resetBoard();
            }
        }

        private boolean checkWinner() {
            // Verificar filas, columnas y diagonales
            return checkRow(row) || checkCol(col) || checkDiagonals();
        }

        private boolean checkRow(int row) {
            return buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                   buttons[row][0].getText().equals(buttons[row][2].getText()) &&
                   !buttons[row][0].getText().isEmpty();
        }

        private boolean checkCol(int col) {
            return buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                   buttons[0][col].getText().equals(buttons[2][col].getText()) &&
                   !buttons[0][col].getText().isEmpty();
        }

        private boolean checkDiagonals() {
            return (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                    !buttons[0][0].getText().isEmpty()) ||
                   (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                    buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                    !buttons[0][2].getText().isEmpty());
        }

        private boolean isBoardFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().isEmpty()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void resetBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }
            xTurn = true; // Reiniciar el turno a 'X'
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true); // Hacer visible la ventana
        });
    }
}

