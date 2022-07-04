package ir.ac.kntu.model.gamepage.stomach.cell;

import ir.ac.kntu.model.Destructible;
import ir.ac.kntu.model.Drawable;
import ir.ac.kntu.model.Movable;
import ir.ac.kntu.model.gamepage.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;

public class Cell implements Drawable, Movable, Destructible {
    private Stomach stomach;

    public Cell(Stomach stomach) {
        this.stomach = stomach;
    }

    public Stomach getStomach() {
        return stomach;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public void move() {

    }

    @Override
    public void destruct() {

    }
}
