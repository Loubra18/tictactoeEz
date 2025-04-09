package be.bralion.tictactoe.model;

import be.bralion.tictactoe.model.exception.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private State state;
    private int numberOfMoves;

    public Game(){
        int default_SIZE = 3;
        this.board = new Board(default_SIZE);
        this.players = new ArrayList<>();
        this.currentPlayer = null;
        this.state = State.NOT_STARTED;
        this.numberOfMoves = 0;
        initializePlayers();
    }
    public Game(int size, List<Player> players, Player currentPlayer, State state) {
        this.board = new Board(size);
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.state = state;
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public int getNumberOfMoves() {
        return numberOfMoves;
    }
    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public void restartGame() {
        this.board.emptyGrid();
        this.state = State.NOT_STARTED;
        initializePlayers();
    }

    public void startGame() {
        if (this.state == State.NOT_STARTED) {
//            Player player1 = new Player("Player 1", new PieceX());
//            Player player2 = new Player("Robot", new PieceO());
//            this.players.add(player1);
//            this.players.add(player2);
//            this.currentPlayer = player1;
            //initializePlayers();
            computeNumberOfMoves();
            this.state = State.IN_PROGRESS;
        }
    }


    public void initializePlayers() {
        this.players.clear();
        Player player1 = new Player("Player 1", new PieceX());
        Player player2 = new Robot("Robot", new PieceO());
        this.players.add(player1);
        this.players.add(player2);
        this.currentPlayer = player1;
    }

    public void changeSymbol() {
        if (this.currentPlayer.getPiece() instanceof PieceX) {
            this.currentPlayer.setPiece(new PieceO());
            this.players.get(1).setPiece(new PieceX());
        } else if (this.currentPlayer.getPiece() instanceof PieceO) {
            this.currentPlayer.setPiece(new PieceX());
            this.players.get(1).setPiece(new PieceO());
        }
    }

    public void playMove(int row, int column) throws Exception {
        if (this.state == State.IN_PROGRESS && this.board.getGrid()[row][column] == null) {
            this.board.getGrid()[row][column] = this.currentPlayer.getPiece();
            computeNumberOfMoves();
            checkWin();
        } else {
            throw new InvalidMoveException("Invalid move: cell already occupied or game not in progress.");
        }
    }

    public void playMoveRobot(){
        if (this.state == State.IN_PROGRESS && this.currentPlayer instanceof Robot) {
            Robot robot = (Robot) this.currentPlayer;
            robot.PlayMove(this);
            computeNumberOfMoves();
            try {
                checkWin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





    //private methods

    public void computeNumberOfMoves() {
        int count = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getGrid()[i][j] != null) {
                    count++;
                }
            }
        }
        this.numberOfMoves = count;
    }

    public void changeCurrentPlayer() {
        if (this.currentPlayer == players.get(0)) {
             this.currentPlayer = players.get(1);
        } else {
             this.currentPlayer = players.getFirst();
        }
    }





    private void checkWin() throws Exception {
        if(this.state != State.IN_PROGRESS) {
            throw new Exception();
        }

        if(this.currentPlayer.getPiece() == null) {
            throw new Exception();
        }


        // Check rows
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRow(i)) {
                this.state = State.WIN;
                return;
            }
        }

        // Check columns
        for (int i = 0; i < board.getSize(); i++) {
            if (checkColumn(i)) {
                this.state = State.WIN;
                return;
            }
        }

        // Check diagonals
        if (checkDiagonal()) {
            this.state = State.WIN;
            return;
        }

        // Check draw
        if (this.numberOfMoves == board.getSize() * board.getSize()) {
            this.state = State.EQUALITY;
        }
    }

    private boolean checkColumn(int i) {
       for(int j = 0; j < board.getSize(); j++) {
           if (board.getGrid()[j][i] != this.currentPlayer.getPiece()) {
               return false;
           }
       }
       return true;
    }

    private boolean checkRow(int i) {
        for (int j = 0; j < board.getSize(); j++) {
            if (board.getGrid()[i][j] != this.currentPlayer.getPiece()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(){
        boolean leftDiagonal = true;
        boolean rightDiagonal = true;

        for(int i = 0; i<board.getSize(); i++) {
            if(board.getGrid()[i][i] != this.currentPlayer.getPiece()) {
                leftDiagonal = false;
            }
            if(board.getGrid()[i][board.getSize()-1-i] != this.currentPlayer.getPiece()) {
                rightDiagonal = false;
            }
        }
        return leftDiagonal || rightDiagonal;
    }




}
