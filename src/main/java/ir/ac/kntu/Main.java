package ir.ac.kntu;

import ir.ac.kntu.model.loginpage.LoginMenu;
import ir.ac.kntu.model.settingpage.SettingMenu;
import ir.ac.kntu.model.gamepage.GamePage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Hossein Yasbolaghi
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(primaryStage);

        SettingMenu settingMenu = new SettingMenu();
        settingMenu.start(primaryStage);

        GamePage gamePage = new GamePage(loginMenu.getUser(), settingMenu.getSetting());
        gamePage.start(primaryStage);
    }
}