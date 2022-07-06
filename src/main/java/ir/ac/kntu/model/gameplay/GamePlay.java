package ir.ac.kntu.model.gameplay;

import ir.ac.kntu.constant.Color;
import ir.ac.kntu.constant.Direction;
import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.constant.GameState;
import ir.ac.kntu.model.SelfMedication;
import ir.ac.kntu.model.gameplay.leftobjects.*;
import ir.ac.kntu.model.gameplay.rightobjects.*;
import ir.ac.kntu.model.gameplay.stomach.Stomach;
import ir.ac.kntu.model.gameplay.stomach.cell.Cell;
import ir.ac.kntu.model.gameplay.stomach.cell.PairCell;
import ir.ac.kntu.model.Setting;
import ir.ac.kntu.model.User;
import ir.ac.kntu.model.gameplay.stomach.cell.SingleCell;
import ir.ac.kntu.model.gameplay.stomach.cell.capsule.*;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.BluePill;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.RedPill;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.YellowPill;
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

import java.security.SecureRandom;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class GamePlay extends Application {
    private final SecureRandom secureRandom = new SecureRandom();

    private User player;

    private final Setting setting;

    private final Canvas canvas;

    private final GraphicsContext gc;

    private final Stomach stomach;

    private final DrMario drMario;

    private final RightBoard rightBoard;

    private final LeftBoard leftBoard;

    private BlueVirus blueVirus;

    private RedVirus redVirus;

    private YellowVirus yellowVirus;

    private final DrCapsule drCapsule;

    private PairCell currentCapsule;

    private PairCell nextCapsule;

    private final ImagePattern mainScene;

    private final Timeline generalTimeline;

    private final Timeline capsuleTimeline;

    private Thread selfMedicationThread;

    private GameState gameState;

    public GamePlay(User user, Setting setting) {
        this.player = player;
        this.setting = setting;
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.stomach = new Stomach();
        initializeStomachViruses();
        this.drMario = new DrMario(600, 220, DrMario.Status.DOC_WAITING3);
        this.rightBoard = new RightBoard(610, 450, 1, GameSpeed.LOW, 4);
        this.leftBoard = new LeftBoard(60, 150, player.getHighScore(), 0);
        this.blueVirus = new BlueVirus(160, 480, BlueVirus.Status.SHAKING);
        this.redVirus = new RedVirus(60, 500, RedVirus.Status.SHAKING);
        this.yellowVirus = new YellowVirus(100, 410, YellowVirus.Status.SHAKING);
        this.drCapsule = new DrCapsule();
        this.mainScene = new ImagePattern(new Image("images/MainScene2.png"));
        this.generalTimeline = new Timeline(new KeyFrame(Duration.millis(200), event -> generalTimeLineStart(gc)));
        this.generalTimeline.setCycleCount(Timeline.INDEFINITE);
        this.capsuleTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> capsuleTimeLineStart()));
        this.capsuleTimeline.setCycleCount(Timeline.INDEFINITE);
        this.gameState = GameState.RUNNING;
    }

    private void initializeStomachViruses() {
        for (int i = 0; i < setting.getVirusLevel() * 4; i++) {
            addVirusToStomach();
        }
    }

    private void addVirusToStomach() {
        int i = secureRandom.nextInt(stomach.getCells().length);
        int j = secureRandom.nextInt(stomach.getCells()[0].length);

        if (stomach.getCells()[i][j] != null || (i < 3 && (j > 4 && j < 9))) {
            addVirusToStomach();
        } else {
            Color[] colors = Color.values();
            switch (colors[secureRandom.nextInt(colors.length)]) {
                case RED -> {
                    stomach.getCells()[i][j] = new ir.ac.kntu.model.gameplay.stomach.cell.virus.RedVirus(stomach, i, j);
                    stomach.addObject(stomach.getCells()[i][j]);
                }
                case BLUE -> {
                    stomach.getCells()[i][j] = new ir.ac.kntu.model.gameplay.stomach.cell.virus.BlueVirus(stomach, i, j);
                    stomach.addObject(stomach.getCells()[i][j]);
                }
                case YELLOW -> {
                    stomach.getCells()[i][j] = new ir.ac.kntu.model.gameplay.stomach.cell.virus.YellowVirus(stomach, i, j);
                    stomach.addObject(stomach.getCells()[i][j]);
                }
                default -> {
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane(canvas);
        Scene scene = new Scene(pane);

        GamePlayEventHandler.getInstance().attachEventHandlers(scene, this);

        primaryStage.setTitle("Dr.Mario");
        primaryStage.setScene(scene);
        primaryStage.show();

        generalTimeline.play();
        capsuleTimeline.play();
    }

    public void generalTimeLineStart(GraphicsContext gc) {
        gc.setFill(mainScene);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        drawObjects(gc);

        if (currentCapsule == null) {
            drMario.setStatus(DrMario.Status.DOC_THROWS);
            if (stomach.getCells()[0][stomach.getCells()[0].length / 2 - 1] != null ||
                    stomach.getCells()[0][stomach.getCells()[0].length / 2] != null) {
                gameState = GameState.LOSE;
            }
            currentCapsule = nextCapsule;
            nextCapsule = null;
            stomach.addObject(currentCapsule);
        }

        checkCurrentCapsule();

        stomach.draw(gc);
    }

    private void drawObjects(GraphicsContext gc) {
        drMario.draw(gc);
        rightBoard.draw(gc);
        leftBoard.draw(gc);
        if (redVirus != null) {
            redVirus.draw(gc);
        }
        if (blueVirus != null) {
            blueVirus.draw(gc);
        }
        if (yellowVirus != null) {
            yellowVirus.draw(gc);
        }

        if (nextCapsule == null) {
            nextCapsule = drCapsule.getCapsule(stomach);
            drMario.setStatus(DrMario.Status.DOC_WAITING3);
        }
        nextCapsule.draw(gc, 570, 205);
    }

    private void capsuleTimeLineStart() {
        stomach.move();
        checkVirusesInStomach();
    }

    public void handlePlayerActions(KeyCode keyCode) {
        if (keyCode == KeyCode.ENTER) {
            if (gameState == GameState.RUNNING) {
                generalTimeline.pause();
                capsuleTimeline.pause();
                gameState = GameState.PAUSE;
            } else if (gameState == GameState.PAUSE) {
                generalTimeline.play();
                capsuleTimeline.play();
                gameState = GameState.RUNNING;
            }
        }

        if (currentCapsule == null || gameState == GameState.PAUSE) {
            return;
        }

        if (keyCode == KeyCode.E) {
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

    private void searchForMatch() {
        for (int i = 0; i < stomach.getCells().length; i++) {
            for (int j = 0; j < stomach.getCells()[0].length; j++) {
                if (stomach.getCells()[i][j] == null) {
                    continue;
                }
                int depth = verticalDepthMeter(i, j, 1);
                if (depth > 3) {
                    for (int k = 0; k < depth; k++) {
                        if (isVirus(i + k, j)) {
                            leftBoard.setScore(leftBoard.getScore() + 100);
                            visualizeVirusDie(i + k, j);
                        }
                        stomach.getCells()[i + k][j].destruct(i + k, j);
                    }
                    continue;
                }
                depth = horizontalDepthMeter(i, j, 1);
                if (depth > 3) {
                    for (int k = 0; k < depth; k++) {
                        if (isVirus(i, j + k)) {
                            leftBoard.setScore(leftBoard.getScore() + 100);
                            visualizeVirusDie(i, j + k);
                        }
                        stomach.getCells()[i][j + k].destruct(i, j + k);
                    }
                }
            }
        }
    }

    private int horizontalDepthMeter(int i, int j, int depth) {
        if ((j + 1) < stomach.getCells()[0].length && stomach.getCells()[i][j + 1] != null &&
                getCellColor(i, j) == getCellColor(i, j + 1)) {
            return horizontalDepthMeter(i, j + 1, depth + 1);
        }

        return depth;
    }

    private int verticalDepthMeter(int i, int j, int depth) {
        if ((i + 1) < stomach.getCells().length && stomach.getCells()[i + 1][j] != null &&
                getCellColor(i, j) == getCellColor(i + 1, j)) {
            return verticalDepthMeter(i + 1, j, depth + 1);
        }

        return depth;
    }

    private boolean isVirus(int i, int j) {
        return stomach.getCells()[i][j] instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.BlueVirus ||
                stomach.getCells()[i][j] instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.RedVirus ||
                stomach.getCells()[i][j] instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.YellowVirus;
    }

    private Color getCellColor(int i, int j) {
        Cell target = stomach.getCells()[i][j];

        if (target instanceof SingleCell singleCell) {
            return getSingleCellColor(singleCell);
        }
        return getPairCellColor(i, j);
    }

    private Color getSingleCellColor(SingleCell target) {
        if (target instanceof BluePill || target instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.BlueVirus) {
            return Color.BLUE;
        }
        if (target instanceof RedPill || target instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.RedVirus) {
            return Color.RED;
        }
        if (target instanceof YellowPill || target instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.YellowVirus) {
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

    private void visualizeVirusDie(int i, int j) {
        Cell target = stomach.getCells()[i][j];
        if (target instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.BlueVirus) {
            blueVirus.setStatus(BlueVirus.Status.DIE);
        } else if (target instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.RedVirus) {
            redVirus.setStatus(RedVirus.Status.DIE);
        } else if (target instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.YellowVirus) {
            yellowVirus.setStatus(YellowVirus.Status.DIE);
        }
    }

    private void checkVirusesInStomach() {
        int redVirusNumber = 0;
        int blueVirusNumber = 0;
        int yellowVirusNumber = 0;
        for (Cell[] cells : stomach.getCells()) {
            for (Cell cell : cells) {
                if (cell instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.RedVirus) {
                    redVirusNumber++;
                } else if (cell instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.BlueVirus) {
                    blueVirusNumber++;
                } else if (cell instanceof ir.ac.kntu.model.gameplay.stomach.cell.virus.YellowVirus) {
                    yellowVirusNumber++;
                }
            }
        }
        if (redVirusNumber == 0) {
            redVirus = null;
        }
        if (blueVirusNumber == 0) {
            blueVirus = null;
        }
        if (yellowVirusNumber == 0) {
            yellowVirus = null;
        }
        if (redVirusNumber + blueVirusNumber + yellowVirusNumber == 0) {
            playerWin();
        }
    }

    private void playerWin() {
        gameState = GameState.WIN;
        player.addTotalMatch();
        if (leftBoard.getScore() > player.getHighScore()) {
            player.setHighScore(leftBoard.getScore());
        }
    }
}
