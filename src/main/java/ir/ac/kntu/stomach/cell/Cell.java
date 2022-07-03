package ir.ac.kntu.stomach.cell;

import ir.ac.kntu.Drawable;
import ir.ac.kntu.Movable;
import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;

public class Cell implements Drawable, Movable {
    private Stomach stomach;

    public Cell(Stomach stomach) {
        this.stomach = stomach;
    }

    public Stomach getStomach() {
        return stomach;
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
