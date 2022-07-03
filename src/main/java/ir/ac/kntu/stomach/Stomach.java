package ir.ac.kntu.stomach;

import ir.ac.kntu.stomach.cell.Cell;

public class Stomach {
    private Cell[][] cells;

    public Stomach(int height, int width) {
        cells = new Cell[height / 16][width / 16];
    }
}
