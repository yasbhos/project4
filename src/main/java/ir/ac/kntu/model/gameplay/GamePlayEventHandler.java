package ir.ac.kntu.model.gameplay;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GamePlayEventHandler {
    private static GamePlayEventHandler instance = new GamePlayEventHandler();

    public static GamePlayEventHandler getInstance() {
        return instance;
    }

    private GamePlayEventHandler() {
    }

    public void attachEventHandlers(Scene scene, GamePlay gamePage) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            gamePage.handlePlayerActions(code);
        });
    }
}
