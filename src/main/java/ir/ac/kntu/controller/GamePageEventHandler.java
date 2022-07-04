package ir.ac.kntu.controller;

import ir.ac.kntu.model.gamepage.GamePage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GamePageEventHandler {
    private static GamePageEventHandler instance = new GamePageEventHandler();

    public static GamePageEventHandler getInstance() {
        return instance;
    }

    private GamePageEventHandler() {
    }

    public void attachEventHandlers(Scene scene, GamePage gamePage) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            gamePage.handlePlayerActions(code);
        });
    }
}
