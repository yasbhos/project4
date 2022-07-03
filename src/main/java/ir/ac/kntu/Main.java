package ir.ac.kntu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Sina Rostami
 */
public class Main extends Application {
    private Canvas canvas = new Canvas(800, 700);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane(canvas);
        Scene scene = new Scene(borderPane);

        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(200), event -> start())
        );
        timelineAnimation.setCycleCount(Timeline.INDEFINITE);

        primaryStage.setTitle("Dr.Mario");
        primaryStage.setScene(scene);
        primaryStage.show();

        timelineAnimation.play();
    }

    public void start() {

    }
}