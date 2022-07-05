package ir.ac.kntu.model.gamepage.stomach.cell.capsule;

import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.PairCell;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.BluePill;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.RedPill;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.YellowPill;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class RedYellowCapsule extends PairCell {
    private final ImagePattern redYellowH = new ImagePattern(new Image("images/stomach/red&yellow1.png"));

    private final ImagePattern redYellowV = new ImagePattern(new Image("images/stomach/red&yellow2.png"));

    private final ImagePattern yellowRedH = new ImagePattern(new Image("images/stomach/red&yellow3.png"));

    private final ImagePattern yellowRedV = new ImagePattern(new Image("images/stomach/red&yellow4.png"));

    public RedYellowCapsule(Stomach stomach, int rowIndex, int columnIndex) {
        super(stomach, rowIndex, columnIndex, PairCell.Status.HORIZONTAL_ONE);
        super.getStomach().getCells()[rowIndex][columnIndex] = this;
        super.getStomach().getCells()[rowIndex][columnIndex + 1] = this;
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (super.getStatus()) {
            case HORIZONTAL_ONE -> {
                gc.setFill(redYellowH);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
            }
            case HORIZONTAL_TWO -> {
                gc.setFill(yellowRedH);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
            }
            case VERTICAL_ONE -> {
                gc.setFill(redYellowV);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS, CELL_DIMENSIONS * 2);
            }
            case VERTICAL_TWO -> {
                gc.setFill(yellowRedV);
                gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                        STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                        CELL_DIMENSIONS, CELL_DIMENSIONS * 2);
            }
            default -> {
            }
        }
    }

    public void draw(GraphicsContext gc, int startX, int startY) {
        gc.setFill(redYellowH);
        gc.fillRect(startX, startY, CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
    }

    @Override
    public void destruct(int i, int j) {
        super.getStomach().removeObject(this);
        super.getStomach().getCells()[i][j] = null;

        switch (super.getStatus()) {
            case HORIZONTAL_ONE, HORIZONTAL_TWO -> {
                if (i == super.getRowIndex() && j == super.getColumnIndex()) {
                    super.getStomach().getCells()[i][j + 1] = null;
                    super.getStomach().addObject(new YellowPill(super.getStomach(), i, j + 1));
                } else {
                    super.getStomach().getCells()[i][j - 1] = null;
                    super.getStomach().addObject(new RedPill(super.getStomach(), i, j - 1));
                }
            }
            case VERTICAL_ONE, VERTICAL_TWO -> {
                if (i == super.getRowIndex() && j == super.getColumnIndex()) {
                    super.getStomach().getCells()[i + 1][j] = null;
                    super.getStomach().addObject(new RedPill(super.getStomach(), i + 1, j));
                } else {
                    super.getStomach().getCells()[i - 1][j] = null;
                    super.getStomach().addObject(new YellowPill(super.getStomach(), i - 1, j));
                }
            }
            default -> {
            }
        }
    }
}
