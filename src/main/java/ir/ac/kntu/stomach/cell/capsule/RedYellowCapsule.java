package ir.ac.kntu.stomach.cell.capsule;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RedYellowCapsule extends Capsule {
    private final ImagePattern redYellowH = new ImagePattern(new Image("images/stomach/red&yellow1.png"));
    private final ImagePattern redYellowV = new ImagePattern(new Image("images/stomach/red&yellow2.png"));
    private final ImagePattern yellowRedH = new ImagePattern(new Image("images/stomach/red&yellow3.png"));
    private final ImagePattern yellowRedV = new ImagePattern(new Image("images/stomach/red&yellow4.png"));

    public RedYellowCapsule(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
