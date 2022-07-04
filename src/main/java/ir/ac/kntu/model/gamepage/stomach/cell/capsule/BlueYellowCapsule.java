package ir.ac.kntu.model.gamepage.stomach.cell.capsule;

import ir.ac.kntu.constant.Direction;
import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.PairCell;
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
    public void move() {
        if (super.getStatus() == Status.HORIZONTAL_ONE || super.getStatus() == Status.HORIZONTAL_TWO) {
            if (super.getRowIndex() >= super.getStomach().getCells().length - 1) {
                return;
            }
            if (super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] != null ||
                    super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex() + 1] != null) {
                return;
            }

            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = null;
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() + 1] = null;
            super.setRowIndex(super.getRowIndex() + 1);
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = this;
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() + 1] = this;
        } else {
            if ((super.getRowIndex() + 1) >= super.getStomach().getCells().length - 1) {
                return;
            }
            if (super.getStomach().getCells()[super.getRowIndex() + 2][super.getColumnIndex()] != null) {
                return;
            }

            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = null;
            super.setRowIndex(super.getRowIndex() + 1);
            super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] = this;
        }
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case DOWN -> move();
            default -> {
            }
        }
    }

    private void moveLeft() {
        if (super.getStatus() == Status.HORIZONTAL_ONE || super.getStatus() == Status.HORIZONTAL_TWO) {
            if (super.getColumnIndex() <= 0) {
                return;
            }
            if (super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() - 1] != null) {
                return;
            }

            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() + 1] = null;
            super.setColumnIndex(super.getColumnIndex() - 1);
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = this;
        } else {
            if (super.getColumnIndex() <= 0) {
                return;
            }
            if (super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() - 1] != null ||
                    super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex() - 1] != null) {
                return;
            }

            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = null;
            super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] = null;
            super.setColumnIndex(super.getColumnIndex() - 1);
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = this;
            super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] = this;
        }
    }

    private void moveRight() {
        if (super.getStatus() == Status.HORIZONTAL_ONE || super.getStatus() == Status.HORIZONTAL_TWO) {
            if (super.getColumnIndex() >= super.getStomach().getCells()[0].length - 2) {
                return;
            }
            if (super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() + 2] != null) {
                return;
            }

            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = null;
            super.setColumnIndex(super.getColumnIndex() + 1);
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() + 1] = this;
        } else {
            if (super.getColumnIndex() >= super.getStomach().getCells()[0].length - 1) {
                return;
            }
            if (super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex() + 1] != null ||
                    super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex() + 1] != null) {
                return;
            }

            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = null;
            super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] = null;
            super.setColumnIndex(super.getColumnIndex() + 1);
            super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = this;
            super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] = this;
        }
    }
}
