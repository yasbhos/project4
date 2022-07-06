package ir.ac.kntu.model.gameplay.stomach;

import ir.ac.kntu.model.Drawable;
import ir.ac.kntu.constant.GlobalConstants;
import ir.ac.kntu.model.Movable;
import ir.ac.kntu.model.gameplay.stomach.cell.Cell;
import javafx.scene.canvas.GraphicsContext;

import static ir.ac.kntu.constant.GlobalConstants.*;

import java.util.ArrayList;

public class Stomach implements Drawable, Movable {
    private Cell[][] cells;

    private ArrayList<Cell> objects;

    public Stomach() {
        cells = new Cell[STOMACH_HEIGHT / CELL_DIMENSIONS][STOMACH_WIDTH / GlobalConstants.CELL_DIMENSIONS];
        this.objects = new ArrayList<>();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void addObject(Cell cell) {
        objects.add(cell);
    }

    public void removeObject(Cell cell) {
        objects.remove(cell);
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (Cell cell : objects) {
            cell.draw(gc);
        }
    }

    @Override
    public void move() {
        for (Cell cell : objects) {
            cell.move();
        }
    }
}
