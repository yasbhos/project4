package ir.ac.kntu;

import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.constant.MusicType;
import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.model.LoginGUI;
import ir.ac.kntu.model.Setting;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.gameplay.GamePlay;
import ir.ac.kntu.model.titlescreen.TitleScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author Hossein Yasbolaghi
 */
public class Main extends Application {
    private static UserDB userDB;

    private static User user;

    public static void main(String[] args) {
        userDB = new UserDB();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Image icon = new Image("images/DrMarioNsPeach.png");
        LoginGUI loginGUI = new LoginGUI(userDB);
        TitleScreen titleScreen = new TitleScreen();
        BorderPane loginPage = new BorderPane();
        loginGUI.addNodesToPane(loginPage);
        Image drMarioBox = new Image("images/DrMarioBox.jpg");
        loginPage.setBackground(new Background(new BackgroundImage(
                drMarioBox, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(450, 600, true, true, false, true)
        )));
        Scene scene = new Scene(loginPage, 450, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dr Mario");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
        ((VBox) loginPage.getCenter()).getChildren().get(2).setOnMouseClicked(event -> {
            user = loginGUI.getUser();
            if (user != null) {
                titleScreen.start(primaryStage);
            }
        });
        final GamePlay[] gamePlay = new GamePlay[1];

        titleScreen.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.ENTER) {
                    gamePlay[0] = new GamePlay(user, new Setting(1, GameSpeed.LOW, MusicType.CHILL));
                    gamePlay[0].start(primaryStage);
                }
            }
        });
    }
}