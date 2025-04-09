package be.bralion.tictactoe.model;

public class Player {
    private String name;
    private Piece piece;

    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void changeSymbol() {
        if(piece instanceof PieceX) {
            piece = new PieceO();
        } else if(piece instanceof PieceO) {
            piece = new PieceX();
        }
    }
}
