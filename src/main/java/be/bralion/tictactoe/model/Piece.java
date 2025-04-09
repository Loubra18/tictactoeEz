package be.bralion.tictactoe.model;

public abstract class Piece {
   private final String symbol;

   public Piece(String symbol) {
         this.symbol = symbol;
   }

    public String getSymbol() {
            return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Piece piece = (Piece) obj;

        return this.symbol.equals(piece.symbol);
    }
}

