package ir.ac.kntu.stomach.cell.capsule;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RedCapsule extends Capsule {
    private final ImagePattern redCapsuleH = new ImagePattern(new Image("images/stomach/redH.png"));
    private final ImagePattern redCapsuleV = new ImagePattern(new Image("images/stomach/redV.png"));

    public RedCapsule(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
