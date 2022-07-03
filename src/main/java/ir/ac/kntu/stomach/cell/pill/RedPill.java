package ir.ac.kntu.stomach.cell.pill;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RedPill extends Pill {
    private final ImagePattern redPill = new ImagePattern(new Image("images/stomach/redPill.png"));

    public RedPill(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
