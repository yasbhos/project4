package ir.ac.kntu.model.gameplay.stomach.cell.capsule;

import ir.ac.kntu.model.gameplay.stomach.Stomach;
import ir.ac.kntu.model.gameplay.stomach.cell.PairCell;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.BluePill;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.RedPill;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.YellowPill;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class BlueYellowCapsule extends PairCell {
    private final ImagePattern blueYellowH = new ImagePattern(new Image("images/stomach/blue&yellow1.png"));

    private final ImagePattern blueYellowV = new ImagePattern(new Image("images/stomach/blue&yellow2.png"));

    private final ImagePattern yellowBlueH = new ImagePattern(new Image("images/stomach/blue&yellow3.png"));

    private final ImagePattern yellowBlueV = new ImagePattern(new Image("images/stomach/blue&yellow4.png"));

    public BlueYellowCapsule(Stomach stomach, int rowIndex, int columnIndex) {
        super(stomach, rowIndex, columnIndex, Status.HORIZONTAL_ONE);
        super.getStomach().getCells()[rowIndex][columnIndex] = this;
        super.getStomach().getCells()[rowIndex][columnIndex + 1] = this;
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (super.getStatus()) {
            case HORIZONTAL_ONE -> {
                gc.setFill(blueYellowH);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
            }
            case HORIZONTAL_TWO -> {
                gc.setFill(yellowBlueH);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
            }
            case VERTICAL_ONE -> {
                gc.setFill(blueYellowV);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS, CELL_DIMENSIONS * 2);
            }
            case VERTICAL_TWO -> {
                gc.setFill(yellowBlueV);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS, CELL_DIMENSIONS * 2);
            }
            default -> {
            }
        }
    }

    public void draw(GraphicsContext gc, int startX, int startY) {
        gc.setFill(blueYellowH);
        gc.fillRect(startX, startY, CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
    }

    @Override
    public void destruct(int i, int j) {
        super.getStomach().removeObject(this);
        super.getStomach().getCells()[i][j] = null;

        switch (super.getStatus()) {
            case HORIZONTAL_ONE -> {
                destruct1(i, j);
            }
            case HORIZONTAL_TWO -> {
                destruct2(i, j);
            }
            case VERTICAL_ONE -> {
                destruct3(i, j);
            }
            case VERTICAL_TWO -> {
                destruct4(i, j);
            }
            default -> {
            }
        }
    }

    private void destruct4(int i, int j) {
        if (i == super.getRowIndex() && j == super.getColumnIndex()) {
            super.getStomach().getCells()[i + 1][j] = null;
            super.getStomach().addObject(new BluePill(super.getStomach(), i + 1, j));
        } else {
            super.getStomach().getCells()[i - 1][j] = null;
            super.getStomach().addObject(new YellowPill(super.getStomach(), i - 1, j));
        }
    }

    private void destruct3(int i, int j) {
        if (i == super.getRowIndex() && j == super.getColumnIndex()) {
            super.getStomach().getCells()[i + 1][j] = null;
            super.getStomach().addObject(new YellowPill(super.getStomach(), i + 1, j));
        } else {
            super.getStomach().getCells()[i - 1][j] = null;
            super.getStomach().addObject(new BluePill(super.getStomach(), i - 1, j));
        }
    }

    private void destruct2(int i, int j) {
        if (i == super.getRowIndex() && j == super.getColumnIndex()) {
            super.getStomach().getCells()[i][j + 1] = null;
            super.getStomach().addObject(new BluePill(super.getStomach(), i, j + 1));
        } else {
            super.getStomach().getCells()[i][j - 1] = null;
            super.getStomach().addObject(new YellowPill(super.getStomach(), i, j - 1));
        }
    }

    private void destruct1(int i, int j) {
        if (i == super.getRowIndex() && j == super.getColumnIndex()) {
            super.getStomach().getCells()[i][j + 1] = null;
            super.getStomach().addObject(new YellowPill(super.getStomach(), i, j + 1));
        } else {
            super.getStomach().getCells()[i][j - 1] = null;
            super.getStomach().addObject(new BluePill(super.getStomach(), i, j - 1));
        }
    }
}
