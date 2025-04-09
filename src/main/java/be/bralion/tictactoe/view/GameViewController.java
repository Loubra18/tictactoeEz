package be.bralion.tictactoe.view;

import be.bralion.tictactoe.controller.GameControllerListener;
import be.bralion.tictactoe.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class GameViewController implements GameControllerListener {
    @FXML
    Button chooseSymbolButton;
    @FXML
    Button restartButton;
    @FXML
    GridPane gridPane;
    @FXML
    Label currentPlayerLabel;
    @FXML
    Button startButton;

    private static Stage stage;
    private GameViewListener listener;

    public static Window GetStage() {
        return stage;
    }

    public static void setStageOf(FXMLLoader fxmlLoader) throws IOException {
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static URL getFXMLResource() {
        URL result =  GameViewController.class.getResource("/be/bralion/tictactoe/tictactoe-view.fxml");
        return result;
    }

    //boucle pour générer le tableau de jeu ?
    //passer un objet Game à la vue pour gérer les cases ?
    public void initialize(int size) {
        createGridPane(size);
        attacheEventToGridPane();
    }

    private void attacheEventToGridPane() {
        for(Node node : gridPane.getChildren()) {
            if(node instanceof Button) {
                node.setOnMouseClicked(event -> {
                    Integer rowIndex = GridPane.getRowIndex(node);
                    Integer columnIndex = GridPane.getColumnIndex(node);

                    if (rowIndex == null) rowIndex = 0;
                    if (columnIndex == null) columnIndex = 0;

                    listener.OnCellClicked(rowIndex, columnIndex);
                });
            }
        }
    }

    private void createGridPane(int size) {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        for (int c = 0; c < size; c++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / size);
            gridPane.getColumnConstraints().add(colConst);
        }

        // Définir les contraintes des lignes
        for (int r = 0; r < size; r++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / size);
            gridPane.getRowConstraints().add(rowConst);
        }

        // Ajouter les boutons dynamiquement
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button btn = new Button();
                btn.setPrefSize(100, 100);
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setHgrow(btn, Priority.ALWAYS);
                GridPane.setVgrow(btn, Priority.ALWAYS);
                GridPane.setRowIndex(btn, row);
                GridPane.setColumnIndex(btn, col);
                gridPane.getChildren().add(btn);
            }
        }
    }
    public static void DisplayError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Bad Move");
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
    private void disableButtons() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }

    private void enableButtons() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(false);
            }
        }
    }

    public void setListener(GameViewListener listener) {
        this.listener = listener;
    }

    @FXML
    public void symbolButtonClicked() {
        listener.symbolChanged();
    }

    @FXML
    public void startButtonClicked() {
        listener.startGame();
        startButton.setDisable(true);
        chooseSymbolButton.setDisable(true);
    }
    
    @FXML
    public void restartButtonClicked() {
        listener.restartGame();
    }

    @Override
    public void onMovePlay(int row, int column, String symbol) {
        for (Node node : gridPane.getChildren()) {
            Integer rowIdx = GridPane.getRowIndex(node);
            Integer colIdx = GridPane.getColumnIndex(node);
            if (rowIdx == null) rowIdx = 0;
            if (colIdx == null) colIdx = 0;

            if (rowIdx == row && colIdx == column && node instanceof Button) {
                ((Button) node).setText(symbol);
                break;
            }
        }
    }

    @Override
    public void onGameEnd(String message) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setHeaderText("Win");
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
        infoAlert.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            listener.restartGame();
        });
        disableButtons();
    }

    @Override
    public void onGameRestart() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setText("");
            }
        }
        enableButtons();
        startButton.setDisable(false);
        chooseSymbolButton.setDisable(false);
    }

    @Override
    public void currentPlayerChanged(String playerSymbol, String name) {
        currentPlayerLabel.setText("Current Player: " + name + " (" + playerSymbol + ")");
    }


    public interface GameViewListener {
        void restartGame();
        void symbolChanged();

        void OnCellClicked(Integer rowIndex, Integer columnIndex);

        void startGame();
    }
}
