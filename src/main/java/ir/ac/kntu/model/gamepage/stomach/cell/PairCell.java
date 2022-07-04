package ir.ac.kntu.model.gamepage.stomach.cell;

import ir.ac.kntu.model.Movable;
import ir.ac.kntu.constant.Direction;
import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.capsule.BlueYellowCapsule;
import javafx.scene.canvas.GraphicsContext;

public class PairCell extends Cell implements Movable {
    public enum Status {
        HORIZONTAL_ONE,
        VERTICAL_ONE,
        HORIZONTAL_TWO,
        VERTICAL_TWO
    }

    private int rowIndex;

    private int columnIndex;

    private Status status;

    public PairCell(Stomach stomach, int rowIndex, int columnIndex, Status status) {
        super(stomach);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.status = status;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void changeStatus() {
        switch (status) {
            case HORIZONTAL_ONE, HORIZONTAL_TWO -> {
                changeHTOV();
            }
            case VERTICAL_ONE, VERTICAL_TWO -> {
                changeVTOH();
            }
            default -> {
            }
        }
    }

    private void changeHTOV() {
        if (rowIndex < 1) {
            return;
        }
        if (super.getStomach().getCells()[rowIndex - 1][columnIndex] != null) {
            return;
        }

        if (status == Status.HORIZONTAL_ONE) {
            status = Status.VERTICAL_ONE;
        } else {
            status = Status.VERTICAL_TWO;
        }

        super.getStomach().getCells()[rowIndex][columnIndex + 1] = null;
        super.getStomach().getCells()[rowIndex - 1][columnIndex] = this;
        this.rowIndex--;
    }

    private void changeVTOH() {
        if (columnIndex >= super.getStomach().getCells()[0].length - 1) {
            return;
        }
        if (super.getStomach().getCells()[rowIndex + 1][columnIndex + 1] != null) {
            return;
        }

        if (status == Status.VERTICAL_ONE) {
            status = Status.HORIZONTAL_TWO;
        } else {
            status = Status.HORIZONTAL_ONE;
        }

        super.getStomach().getCells()[rowIndex][columnIndex] = null;
        super.getStomach().getCells()[rowIndex + 1][columnIndex + 1] = this;
        this.rowIndex++;
    }

    public void draw(GraphicsContext gc, int startX, int startY) {

    }

    @Override
    public void move() {

    }

    public void move(Direction direction) {

    }
}
