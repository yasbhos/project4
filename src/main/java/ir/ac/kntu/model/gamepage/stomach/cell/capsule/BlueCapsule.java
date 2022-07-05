package ir.ac.kntu.model.gamepage.stomach.cell.capsule;

import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.PairCell;
import ir.ac.kntu.model.gamepage.stomach.cell.pill.BluePill;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class BlueCapsule extends PairCell {
    private final ImagePattern blueCapsuleH = new ImagePattern(new Image("images/stomach/blueH.png"));

    private final ImagePattern blueCapsuleV = new ImagePattern(new Image("images/stomach/blueV.png"));

    public BlueCapsule(Stomach stomach, int rowIndex, int columnIndex) {
        super(stomach, rowIndex, columnIndex, Status.HORIZONTAL_ONE);
        super.getStomach().getCells()[rowIndex][columnIndex] = this;
        super.getStomach().getCells()[rowIndex][columnIndex + 1] = this;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (super.getStatus() == Status.HORIZONTAL_ONE || super.getStatus() == Status.HORIZONTAL_TWO) {
            gc.setFill(blueCapsuleH);
            gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                    STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                    CELL_DIMENSIONS * 2, CELL_DIMENSIONS);
        } else {
            gc.setFill(blueCapsuleV);
            gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                    STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                    CELL_DIMENSIONS, CELL_DIMENSIONS * 2);
        }
    }

    public void draw(GraphicsContext gc, int startX, int startY) {
        gc.setFill(blueCapsuleH);
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
                    super.getStomach().addObject(new BluePill(super.getStomach(), i, j + 1));
                } else {
                    super.getStomach().getCells()[i][j - 1] = null;
                    super.getStomach().addObject(new BluePill(super.getStomach(), i, j - 1));
                }
            }
            case VERTICAL_ONE, VERTICAL_TWO -> {
                if (i == super.getRowIndex() && j == super.getColumnIndex()) {
                    super.getStomach().getCells()[i + 1][j] = null;
                    super.getStomach().addObject(new BluePill(super.getStomach(), i + 1, j));
                } else {
                    super.getStomach().getCells()[i - 1][j] = null;
                    super.getStomach().addObject(new BluePill(super.getStomach(), i - 1, j));
                }
            }
            default -> {
            }
        }
    }
}
