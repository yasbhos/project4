package ir.ac.kntu.model.titlescreen;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TitleScreenEventHandler {
    private static TitleScreenEventHandler instance = new TitleScreenEventHandler();

    public static TitleScreenEventHandler getInstance() {
        return instance;
    }

    private TitleScreenEventHandler() {
    }

    public void attachEventHandlers(Scene scene, TitleScreen titleScreen) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            titleScreen.handlePlayerActions(code);
        });
    }
}
