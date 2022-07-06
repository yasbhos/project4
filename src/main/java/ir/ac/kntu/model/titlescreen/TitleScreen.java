package ir.ac.kntu.model.titlescreen;

import ir.ac.kntu.constant.GlobalConstants;
import ir.ac.kntu.model.gameplay.leftobjects.BlueVirus;
import ir.ac.kntu.model.gameplay.rightobjects.DrMario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TitleScreen extends Application {
    private final Scene scene;

    private final Canvas canvas;

    private final GraphicsContext gc;

    private final Logo logo;

    private final DrMario drMario;

    private final BlueVirus blueVirus;

    private final Heart heart;

    private final Timeline timeline;

    private final ImagePattern mainScene;

    public TitleScreen() {
        canvas = new Canvas(GlobalConstants.CANVAS_WIDTH, GlobalConstants.CANVAS_HEIGHT);
        scene = new Scene(new Pane(canvas));
        gc = canvas.getGraphicsContext2D();
        logo = new Logo(220, 150);
        drMario = new DrMario(140, 500, DrMario.Status.DOC_WAITING1);
        blueVirus = new BlueVirus(610, 530, BlueVirus.Status.SHAKING);
        heart = new Heart(220, 485);
        timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> start(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        mainScene = new ImagePattern(new Image("images/Dr_Mario_NES_title_screen.png"));
    }

    @Override
    public void start(Stage primaryStage) {
        TitleScreenEventHandler.getInstance().attachEventHandlers(scene, this);

        primaryStage.setTitle("Dr.Mario");
        primaryStage.setScene(scene);
        primaryStage.show();

        timeline.play();
    }

    private void start(GraphicsContext gc) {
        gc.setFill(mainScene);
        gc.fillRect(0, 0, GlobalConstants.CANVAS_WIDTH, GlobalConstants.CANVAS_HEIGHT);

        logo.draw(gc);
        drMario.draw(gc);
        blueVirus.draw(gc);
        heart.draw(gc);

        if (drMario.getStatus() == DrMario.Status.DOC_WAITING1) {
            drMario.setStatus(DrMario.Status.DOC_WAITING2);
        } else {
            drMario.setStatus(DrMario.Status.DOC_WAITING1);
        }
    }

    public void handlePlayerActions(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) {
            return;
        } else if (keyCode == KeyCode.DOWN) {
            return;
        }
    }

    public Scene getScene() {
        return scene;
    }
}
