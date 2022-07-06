package ir.ac.kntu.model.gameplay.stomach.cell;

import ir.ac.kntu.model.Movable;
import ir.ac.kntu.constant.Direction;
import ir.ac.kntu.model.gameplay.stomach.Stomach;
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
        if (status == Status.HORIZONTAL_ONE || status == Status.HORIZONTAL_TWO) {
            if (rowIndex >= super.getStomach().getCells().length - 1) {
                return;
            }
            if (super.getStomach().getCells()[rowIndex + 1][columnIndex] != null ||
                    super.getStomach().getCells()[rowIndex + 1][columnIndex + 1] != null) {
                return;
            }

            super.getStomach().getCells()[rowIndex][columnIndex] = null;
            super.getStomach().getCells()[rowIndex][columnIndex + 1] = null;
            rowIndex++;
            super.getStomach().getCells()[rowIndex][columnIndex] = this;
            super.getStomach().getCells()[rowIndex][columnIndex + 1] = this;
        } else {
            if ((rowIndex + 1) >= super.getStomach().getCells().length - 1) {
                return;
            }
            if (super.getStomach().getCells()[rowIndex + 2][columnIndex] != null) {
                return;
            }

            super.getStomach().getCells()[rowIndex][columnIndex] = null;
            rowIndex++;
            super.getStomach().getCells()[rowIndex + 1][columnIndex] = this;
        }
    }

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
        if (status == Status.HORIZONTAL_ONE || status == Status.HORIZONTAL_TWO) {
            if (columnIndex <= 0) {
                return;
            }
            if (super.getStomach().getCells()[rowIndex][columnIndex - 1] != null) {
                return;
            }

            super.getStomach().getCells()[rowIndex][columnIndex + 1] = null;
            columnIndex--;
            super.getStomach().getCells()[rowIndex][columnIndex] = this;
        } else {
            if (columnIndex <= 0) {
                return;
            }
            if (super.getStomach().getCells()[rowIndex][columnIndex - 1] != null ||
                    super.getStomach().getCells()[rowIndex + 1][columnIndex - 1] != null) {
                return;
            }

            super.getStomach().getCells()[rowIndex][columnIndex] = null;
            super.getStomach().getCells()[rowIndex + 1][columnIndex] = null;
            columnIndex--;
            super.getStomach().getCells()[rowIndex][columnIndex] = this;
            super.getStomach().getCells()[rowIndex + 1][columnIndex] = this;
        }
    }

    private void moveRight() {
        if (status == Status.HORIZONTAL_ONE || status == Status.HORIZONTAL_TWO) {
            if (columnIndex >= super.getStomach().getCells()[0].length - 2) {
                return;
            }
            if (super.getStomach().getCells()[rowIndex][columnIndex + 2] != null) {
                return;
            }

            super.getStomach().getCells()[rowIndex][columnIndex] = null;
            columnIndex++;
            super.getStomach().getCells()[rowIndex][columnIndex + 1] = this;
        } else {
            if (columnIndex >= super.getStomach().getCells()[0].length - 1) {
                return;
            }
            if (super.getStomach().getCells()[rowIndex][columnIndex + 1] != null ||
                    super.getStomach().getCells()[rowIndex + 1][columnIndex + 1] != null) {
                return;
            }

            super.getStomach().getCells()[rowIndex][columnIndex] = null;
            super.getStomach().getCells()[rowIndex + 1][columnIndex] = null;
            columnIndex++;
            super.getStomach().getCells()[rowIndex][columnIndex] = this;
            super.getStomach().getCells()[rowIndex + 1][columnIndex] = this;
        }
    }
}
