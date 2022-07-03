package ir.ac.kntu.stomach.cell.capsule;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class BlueCapsule extends Capsule {
    private final ImagePattern blueCapsuleH = new ImagePattern(new Image("images/stomach/blueH.png"));
    private final ImagePattern blueCapsuleV = new ImagePattern(new Image("images/stomach/blueV.png"));

    public BlueCapsule(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
