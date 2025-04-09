package be.bralion.tictactoe.model;

public class Robot extends Player {
    public Robot(String name, Piece piece) {
        super(name, piece);
    }

    public void PlayMove(Game game) {
        Board board = game.getBoard();
        int size = board.getSize();
        Piece[][] grid = board.getGrid();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = this.getPiece();
                    return;
                }
            }
        }
    }
}
