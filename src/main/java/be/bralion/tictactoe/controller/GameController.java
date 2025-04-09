package be.bralion.tictactoe.controller;

import be.bralion.tictactoe.model.*;
import be.bralion.tictactoe.model.exception.InvalidMoveException;
import be.bralion.tictactoe.view.GameViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;

public class GameController extends Application implements GameViewController.GameViewListener {
    private final FXMLLoader fxmlLoader = new FXMLLoader(GameViewController.getFXMLResource());
    private final Game gameInstance;
    private GameControllerListener gameControllerListener;

    public GameController() {
        this.gameInstance = new Game();
        this.gameInstance.setState(State.NOT_STARTED);

    }
    public void setGameControllerListener(GameControllerListener listener) {
        this.gameControllerListener = listener;
    }

    public void sendCurrentPlayer() {
        Player currentPlayer = this.gameInstance.getCurrentPlayer();
        if (currentPlayer != null) {
            gameControllerListener.currentPlayerChanged(currentPlayer.getName(), currentPlayer.getPiece().getSymbol());
        }
    }


    @Override
    public void restartGame() {
        this.gameInstance.restartGame();
        this.gameControllerListener.onGameRestart();
        sendCurrentPlayer();

    }

    @Override
    public void symbolChanged() {

        if(this.gameInstance.getState() == State.NOT_STARTED) {
            this.gameInstance.changeSymbol();
            System.out.println("Partie démarrée !");
            System.out.println("Symbole du joueur courant changé à" + this.gameInstance.getCurrentPlayer().getPiece().getSymbol());
            sendCurrentPlayer();
        } else {
            GameViewController.DisplayError("La partie a déjà commencé !");
        }

        //this.gameInstance.getCurrentPlayer().changeSymbol();

    }

    @Override
    public void startGame() {
        if(this.gameInstance.getState() == State.NOT_STARTED) {
            this.gameInstance.startGame();
        } else {
            GameViewController.DisplayError("La partie a déjà commencé !");
        }
    }



    @Override
    public void OnCellClicked(Integer rowIndex, Integer columnIndex) {
        try{
            if(this.gameInstance.getState() == State.NOT_STARTED) {
                GameViewController.DisplayError("La partie n'a pas encore commencé !");
                return;
            }
            Piece currentPiece = this.gameInstance.getCurrentPlayer().getPiece();
            this.gameInstance.playMove(rowIndex, columnIndex);

            if (gameControllerListener != null) {
                gameControllerListener.onMovePlay(rowIndex, columnIndex, currentPiece.getSymbol());
            }

            // Game end handling
            if (gameInstance.getState() == State.WIN) {
                gameControllerListener.onGameEnd("Le joueur " + gameInstance.getCurrentPlayer().getName() + " a gagné !");
            } else if (gameInstance.getState() == State.EQUALITY) {
                gameControllerListener.onGameEnd("Match nul !");
            } else {
                // Change current player
                gameInstance.changeCurrentPlayer();
                sendCurrentPlayer();
//                if(this.gameInstance.getCurrentPlayer() instanceof Robot) {
//                    this.gameInstance.playMoveRobot();
//                    gameControllerListener.onMovePlay(rowIndex, columnIndex, currentPiece.getSymbol());
//                    if (gameInstance.getState() == State.WIN) {
//                        gameControllerListener.onGameEnd("Le joueur " + gameInstance.getCurrentPlayer().getName() + " a gagné !");
//                    } else if (gameInstance.getState() == State.EQUALITY) {
//                        gameControllerListener.onGameEnd("Match nul !");
//                    } else {
//                        // Change current player
//                        gameInstance.changeCurrentPlayer();
//                        sendCurrentPlayer();
//                    }
//                }
            }

        } catch (InvalidMoveException e){
            GameViewController.DisplayError(e.getMessage());
        } catch (Exception e){
            GameViewController.DisplayError("An error occurred: " + e.getMessage());
        }
    }



    @Override
    public void start(Stage stage) throws IOException {
        GameViewController.setStageOf(fxmlLoader);
        GameViewController gameViewController = fxmlLoader.getController();
        gameViewController.setListener(this);
        this.setGameControllerListener(gameViewController);

        gameViewController.initialize(3);
        sendCurrentPlayer();


    }

    public static void main(String[] args) {
        launch();
    }
}
