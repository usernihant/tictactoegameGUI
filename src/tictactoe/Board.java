package tictactoe;


import java.util.ArrayList;


public class Board {
    private ArrayList<Square> allSquares;
    private int size;

    public Board(int size) {
        this.size = size;
        allSquares = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                allSquares.add(new Square(row, col));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Square> getAllSquares() {
        return allSquares;
    }

    public Square getSquare(int row, int col) {
        for (Square square : allSquares) {
            if (square.getRow() == row && square.getCol() == col) {
                return square;
            }
        }
        return null;
    }

//    public void addSquare(Square s) {
//        allSquares.add(s);
//    }
//
//    public void removeSquare(Square s) {
//        allSquares.remove(s);
//    }

//    public void printBoard() {
//        System.out.println("---*---*---*---*--");
//        System.out.println("    Tic Tac Toe    ");
//        System.out.println("------------------");
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                Square s = getSquare(row, col);
//                System.out.print(" | " + s.getValue() + " ");
//            }
//            System.out.println(" |");
//            System.out.println("------------------");
//        }
//        System.out.println("---*---*---*---*--");
//    }
}
