package be.bralion.tictactoe.model;

public class Robot extends Player {
    public Robot(String name, Piece piece) {
        super(name, piece);
    }

    public int[] PlayMove(Game game) throws InterruptedException {
        Board board = game.getBoard();
        int size = board.getSize();
        Piece[][] grid = board.getGrid();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = this.getPiece();
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
