package be.bralion.tictactoe.model;

public class Board {
    private Piece[][] grid;
    private int size;


    public Board(int size) {
        this.size = size;
        this.grid = new Piece[size][size];
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public void setGrid(Piece[][] grid) {
        this.grid = grid;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void emptyGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }
}
