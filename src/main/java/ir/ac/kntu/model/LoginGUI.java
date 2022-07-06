package ir.ac.kntu.model;

import ir.ac.kntu.db.UserDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LoginGUI {
    private UserDB userDB;

    private ScrollPane scrollPane;

    private VBox vBox;

    public LoginGUI(UserDB userDB) {
        this.userDB = userDB;
        createTopPanel();
        createCenterPanel();
    }

    private void createTopPanel() {
        scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(250);
        scrollPane.setMaxHeight(200);
        scrollPane.setPrefHeight(100);
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15));
        for (int i = 0; i < userDB.getSize(); i++) {
            Label username = new Label(userDB.getUserByIndex(i).getUsername() +
                    " -> HIGH SCORE: " + userDB.getUserByIndex(i).getHighScore());
            username.setOnMouseClicked(event -> usernameLabelEventHandler(username));
            vBox.getChildren().add(username);
        }
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);
    }

    private void usernameLabelEventHandler(Label target) {
        String username = target.getText().split("\\s+->\\s+")[0];
        ((TextField) vBox.getChildren().get(0)).setText(username);
    }

    private void createCenterPanel() {
        vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15));
        TextField username = new TextField();
        username.setPromptText("username");
        username.setMaxWidth(200);
        username.setPrefWidth(100);
        VBox.setVgrow(username, Priority.ALWAYS);
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setMaxWidth(200);
        password.setPrefWidth(100);
        VBox.setVgrow(password, Priority.ALWAYS);
        Button signUpButton = new Button("SIGN UP");

        vBox.getChildren().addAll(username, password, signUpButton);
    }

    public void addNodesToPane(BorderPane borderPane) {
        borderPane.setTop(scrollPane);
        BorderPane.setAlignment(scrollPane, Pos.CENTER);

        borderPane.setCenter(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);

        borderPane.setPadding(new Insets(30));
    }

    public User getUser() {
        String username = ((TextField) vBox.getChildren().get(0)).getText();
        String password = ((PasswordField) vBox.getChildren().get(1)).getText();
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        User user = userDB.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            return user;
        }

        user = userDB.getUserByUsername(username);
        if (user == null) {
            user = new User(username, password);
            userDB.addUser(user);
            return user;
        }

        return null;
    }
}
