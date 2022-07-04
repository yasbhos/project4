package ir.ac.kntu.model.gamepage.stomach.cell;

import ir.ac.kntu.model.gamepage.stomach.Stomach;

public class SingleCell extends Cell {
    private int rowIndex;

    private int columnIndex;

    public SingleCell(Stomach stomach, int rowIndex, int columnIndex) {
        super(stomach);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
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
}
