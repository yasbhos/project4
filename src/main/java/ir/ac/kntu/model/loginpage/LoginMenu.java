package ir.ac.kntu.model.loginpage;

import ir.ac.kntu.db.UserDB;
import ir.ac.kntu.model.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    private UserDB userDB;

    private User user = new User("funlife", "2126");

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public User getUser() {
        return user;
    }
}
