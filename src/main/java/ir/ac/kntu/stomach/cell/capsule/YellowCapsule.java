package ir.ac.kntu.stomach.cell.capsule;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class YellowCapsule extends Capsule {
    private final ImagePattern yellowCapsuleH = new ImagePattern(new Image("images/stomach/yellowH.png"));
    private final ImagePattern yellowCapsuleV = new ImagePattern(new Image("images/stomach/yellowV.png"));

    public YellowCapsule(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
