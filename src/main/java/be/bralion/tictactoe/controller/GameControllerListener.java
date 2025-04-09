package be.bralion.tictactoe.controller;

import be.bralion.tictactoe.model.Player;

public interface GameControllerListener {
    void onMovePlay(int row, int column, String symbol);
    void onGameEnd(String message);
    void onGameRestart();
    void currentPlayerChanged(String playerSymbol, String name);
}
