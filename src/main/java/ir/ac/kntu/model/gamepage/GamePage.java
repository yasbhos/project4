package ir.ac.kntu.model.gamepage;

import ir.ac.kntu.constant.Direction;
import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.controller.GamePageEventHandler;
import ir.ac.kntu.model.gamepage.leftobjects.*;
import ir.ac.kntu.model.gamepage.rightobjects.*;
import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.PairCell;
import ir.ac.kntu.model.Setting;
import ir.ac.kntu.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class GamePage extends Application {
    private User player;

    private Setting setting;

    private Canvas canvas;

    private GraphicsContext gc;

    private Stomach stomach;

    private DrMario drMario;

    private RightBoard rightBoard;

    private LeftBoard leftBoard;

    private BlueVirus blueVirus;

    private RedVirus redVirus;

    private YellowVirus yellowVirus;

    private DrCapsule drCapsule;

    private PairCell currentCapsule;

    private PairCell nextCapsule;

    private ImagePattern mainScene = new ImagePattern(new Image("images/MainScene2.png"));

    private Timeline generalTimeline;

    private Timeline capsuleTimeline;

    public GamePage(User player, Setting setting) {
        this.player = player;
        this.setting = setting;
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.stomach = new Stomach();
        this.drMario = new DrMario(600, 220, DrMario.Status.DOC_WAITING);
        this.rightBoard = new RightBoard(610, 450, 1, GameSpeed.LOW, 4);
        this.leftBoard = new LeftBoard(60, 150, 1000, 0);
        this.blueVirus = new BlueVirus(160, 480, BlueVirus.Status.SHAKING);
        this.redVirus = new RedVirus(60, 500, RedVirus.Status.SHAKING);
        this.yellowVirus = new YellowVirus(100, 410, YellowVirus.Status.SHAKING);
        this.drCapsule = new DrCapsule();
        this.generalTimeline = new Timeline(new KeyFrame(Duration.millis(250), event -> start(gc)));
        this.generalTimeline.setCycleCount(Timeline.INDEFINITE);
        this.capsuleTimeline = new Timeline(new KeyFrame(Duration.millis(1250), event -> stomach.move()));
        this.capsuleTimeline.setCycleCount(Timeline.INDEFINITE);
    }


    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane(canvas);
        Scene scene = new Scene(borderPane);

        GamePageEventHandler.getInstance().attachEventHandlers(scene, this);

        primaryStage.setTitle("Dr.Mario");
        primaryStage.setScene(scene);
        primaryStage.show();

        generalTimeline.play();
        capsuleTimeline.play();
    }

    public void start(GraphicsContext gc) {
        gc.setFill(mainScene);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        drMario.draw(gc);
        rightBoard.draw(gc);
        leftBoard.draw(gc);
        redVirus.draw(gc);
        blueVirus.draw(gc);
        yellowVirus.draw(gc);

        if (nextCapsule == null) {
            nextCapsule = drCapsule.getCapsule(stomach);
            drMario.setStatus(DrMario.Status.DOC_WAITING);
        }
        nextCapsule.draw(gc, 570, 205);

        if (currentCapsule == null) {
            drMario.setStatus(DrMario.Status.DOC_THROWS);
            currentCapsule = nextCapsule;
            nextCapsule = null;
            stomach.addObject(currentCapsule);
        }

        checkCurrentCapsule();

        stomach.draw(gc);
    }

    public void handlePlayerActions(KeyCode keyCode) {
        if (currentCapsule == null) {
            return;
        }

        if (keyCode == KeyCode.ENTER) {
            pause();
        } else if (keyCode == KeyCode.E) {
            currentCapsule.changeStatus();
        } else if (keyCode == KeyCode.LEFT) {
            currentCapsule.move(Direction.LEFT);
        } else if (keyCode == KeyCode.RIGHT) {
            currentCapsule.move(Direction.RIGHT);
        } else if (keyCode == KeyCode.DOWN) {
            currentCapsule.move(Direction.DOWN);
        }
    }

    private void checkCurrentCapsule() {
        int i = currentCapsule.getRowIndex();
        int j = currentCapsule.getColumnIndex();

        switch (currentCapsule.getStatus()) {
            case HORIZONTAL_ONE, HORIZONTAL_TWO -> {
                if (i >= stomach.getCells().length - 1) {
                    currentCapsule = null;
                    return;
                }
                if (stomach.getCells()[i + 1][j] != null || stomach.getCells()[i + 1][j + 1] != null) {
                    currentCapsule = null;
                }
            }
            case VERTICAL_ONE, VERTICAL_TWO -> {
                if (i + 1 >= stomach.getCells().length - 1) {
                    currentCapsule = null;
                    return;
                }
                if (stomach.getCells()[i + 2][j] != null) {
                    currentCapsule = null;
                }
            }
            default -> {
            }
        }
    }

    private void pause() {

    }
}
