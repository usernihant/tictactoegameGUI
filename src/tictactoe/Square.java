package tictactoe;

public class Square {
    private int row;
    private int col;
    private char value;

    public Square(int row, int col) {
        this(row, col, '-');
    }

    public Square(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isNeighbor(Square s) {
        int rowDiff = Math.abs(this.row - s.row);
        int colDiff = Math.abs(this.col - s.col);
        return rowDiff == 1 && colDiff == 1;
    }
}
