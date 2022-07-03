package ir.ac.kntu.stomach.cell.pill;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class YellowPill extends Pill {
    private final ImagePattern yellowPill = new ImagePattern(new Image("images/stomach/yellowPill.png"));

    public YellowPill(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
