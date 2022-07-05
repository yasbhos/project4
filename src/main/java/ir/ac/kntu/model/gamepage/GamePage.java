package ir.ac.kntu.model.gamepage;

import ir.ac.kntu.constant.Color;
import ir.ac.kntu.constant.Direction;
import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.controller.GamePageEventHandler;
import ir.ac.kntu.model.gamepage.leftobjects.*;
import ir.ac.kntu.model.gamepage.rightobjects.*;
import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.Cell;
import ir.ac.kntu.model.gamepage.stomach.cell.PairCell;
import ir.ac.kntu.model.Setting;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.gamepage.stomach.cell.SingleCell;
import ir.ac.kntu.model.gamepage.stomach.cell.capsule.*;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.BluePill;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.RedPill;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.YellowPill;
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

import java.security.SecureRandom;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class GamePage extends Application {
    private final SecureRandom secureRandom = new SecureRandom();

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
        initializeStomachViruses();
        this.drMario = new DrMario(600, 220, DrMario.Status.DOC_WAITING);
        this.rightBoard = new RightBoard(610, 450, 1, GameSpeed.LOW, 4);
        this.leftBoard = new LeftBoard(60, 150, 1000, 0);
        this.blueVirus = new BlueVirus(160, 480, BlueVirus.Status.SHAKING);
        this.redVirus = new RedVirus(60, 500, RedVirus.Status.SHAKING);
        this.yellowVirus = new YellowVirus(100, 410, YellowVirus.Status.SHAKING);
        this.drCapsule = new DrCapsule();
        this.generalTimeline = new Timeline(new KeyFrame(Duration.millis(200), event -> start(gc)));
        this.generalTimeline.setCycleCount(Timeline.INDEFINITE);
        this.capsuleTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> stomach.move()));
        this.capsuleTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void initializeStomachViruses() {
        for (int i = 0; i < setting.getVirusLevel() * 4; i++) {
            addVirusToStomach();
        }
    }

    private void addVirusToStomach() {
        int i = secureRandom.nextInt(stomach.getCells().length);
        int j = secureRandom.nextInt(stomach.getCells()[0].length);

        if (stomach.getCells()[i][j] != null) {
            addVirusToStomach();
        } else {
            Color[] colors = Color.values();
            switch (colors[secureRandom.nextInt(colors.length)]) {
                case RED -> {
                    stomach.getCells()[i][j] = new ir.ac.kntu.model.gamepage.stomach.cell.virus.RedVirus(stomach, i, j);
                    stomach.addObject(stomach.getCells()[i][j]);
                }
                case BLUE -> {
                    stomach.getCells()[i][j] = new ir.ac.kntu.model.gamepage.stomach.cell.virus.BlueVirus(stomach, i, j);
                    stomach.addObject(stomach.getCells()[i][j]);
                }
                case YELLOW -> {
                    stomach.getCells()[i][j] = new ir.ac.kntu.model.gamepage.stomach.cell.virus.YellowVirus(stomach, i, j);
                    stomach.addObject(stomach.getCells()[i][j]);
                }
                default -> {
                }
            }
        }
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
                    searchForMatch();
                } else if (stomach.getCells()[i + 1][j] != null || stomach.getCells()[i + 1][j + 1] != null) {
                    currentCapsule = null;
                    searchForMatch();
                }
            }
            case VERTICAL_ONE, VERTICAL_TWO -> {
                if (i + 1 >= stomach.getCells().length - 1) {
                    currentCapsule = null;
                    searchForMatch();
                } else if (stomach.getCells()[i + 2][j] != null) {
                    currentCapsule = null;
                    searchForMatch();
                }
            }
            default -> {
            }
        }
    }

    private void pause() {

    }

    private void searchForMatch() {
        for (int i = 0; i < stomach.getCells().length; i++) {
            for (int j = 0; j < stomach.getCells()[0].length; j++) {
                if (stomach.getCells()[i][j] == null) {
                    continue;
                }

                int depth = depthMeter(i, j, 1);
                if (depth < 4) {
                    continue;
                }

                for (int k = 0; k < depth; k++) {
                    if (isVirus(i + k, j)) {
                        leftBoard.setScore(leftBoard.getScore() + 100);
                    }
                    stomach.getCells()[i + k][j].destruct(i + k, j);
                }
            }
        }
    }

    private int depthMeter(int i, int j, int depth) {
        if ((i + 1) < stomach.getCells().length && stomach.getCells()[i + 1][j] != null &&
                getCellColor(i, j) == getCellColor(i + 1, j)) {
            return depthMeter(i + 1, j, depth + 1);
        }

        return depth;
    }

    private boolean isVirus(int i, int j) {
        return stomach.getCells()[i][j] instanceof ir.ac.kntu.model.gamepage.stomach.cell.virus.BlueVirus ||
                stomach.getCells()[i][j] instanceof ir.ac.kntu.model.gamepage.stomach.cell.virus.RedVirus ||
                stomach.getCells()[i][j] instanceof ir.ac.kntu.model.gamepage.stomach.cell.virus.YellowVirus;
    }

    private Color getCellColor(int i, int j) {
        Cell target = stomach.getCells()[i][j];

        if (target instanceof SingleCell singleCell) {
            return getSingleCellColor(singleCell);
        }
        return getPairCellColor(i, j);
    }

    private Color getSingleCellColor(SingleCell target) {
        if (target instanceof BluePill || target instanceof ir.ac.kntu.model.gamepage.stomach.cell.virus.BlueVirus) {
            return Color.BLUE;
        }
        if (target instanceof RedPill || target instanceof ir.ac.kntu.model.gamepage.stomach.cell.virus.RedVirus) {
            return Color.RED;
        }
        if (target instanceof YellowPill || target instanceof ir.ac.kntu.model.gamepage.stomach.cell.virus.YellowVirus) {
            return Color.YELLOW;
        }

        return null;
    }

    private Color getPairCellColor(int i, int j) {
        PairCell target = (PairCell) stomach.getCells()[i][j];

        if (target instanceof BlueCapsule) {
            return Color.BLUE;
        }
        if (target instanceof RedCapsule) {
            return Color.RED;
        }
        if (target instanceof YellowCapsule) {
            return Color.YELLOW;
        }

        if (target instanceof RedBlueCapsule) {
            return getRBCapsuleColor(i, j);
        }
        if (target instanceof RedYellowCapsule) {
            return getRYCapsuleColor(i, j);
        }
        if (target instanceof BlueYellowCapsule) {
            return getBYCapsuleColor(i, j);
        }

        return null;
    }

    private Color getRBCapsuleColor(int i, int j) {
        RedBlueCapsule rb = (RedBlueCapsule) stomach.getCells()[i][j];

        if (rb.getRowIndex() == i && rb.getColumnIndex() == j) {
            return switch (rb.getStatus()) {
                case HORIZONTAL_ONE, VERTICAL_ONE -> Color.RED;
                case HORIZONTAL_TWO, VERTICAL_TWO -> Color.BLUE;
            };
        } else {
            return switch (rb.getStatus()) {
                case HORIZONTAL_ONE, VERTICAL_ONE -> Color.BLUE;
                case HORIZONTAL_TWO, VERTICAL_TWO -> Color.RED;
            };
        }
    }

    private Color getRYCapsuleColor(int i, int j) {
        RedYellowCapsule ry = (RedYellowCapsule) stomach.getCells()[i][j];

        if (ry.getRowIndex() == i && ry.getColumnIndex() == j) {
            return switch (ry.getStatus()) {
                case HORIZONTAL_ONE, VERTICAL_ONE -> Color.RED;
                case HORIZONTAL_TWO, VERTICAL_TWO -> Color.YELLOW;
            };
        } else {
            return switch (ry.getStatus()) {
                case HORIZONTAL_ONE, VERTICAL_ONE -> Color.YELLOW;
                case HORIZONTAL_TWO, VERTICAL_TWO -> Color.RED;
            };
        }
    }

    private Color getBYCapsuleColor(int i, int j) {
        BlueYellowCapsule by = (BlueYellowCapsule) stomach.getCells()[i][j];

        if (by.getRowIndex() == i && by.getColumnIndex() == j) {
            return switch (by.getStatus()) {
                case HORIZONTAL_ONE, VERTICAL_ONE -> Color.BLUE;
                case HORIZONTAL_TWO, VERTICAL_TWO -> Color.YELLOW;
            };
        } else {
            return switch (by.getStatus()) {
                case HORIZONTAL_ONE, VERTICAL_ONE -> Color.YELLOW;
                case HORIZONTAL_TWO, VERTICAL_TWO -> Color.BLUE;
            };
        }
    }
}
