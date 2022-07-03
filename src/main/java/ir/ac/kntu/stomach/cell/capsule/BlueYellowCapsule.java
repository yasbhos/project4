package ir.ac.kntu.stomach.cell.capsule;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class BlueYellowCapsule extends Capsule {
    private final ImagePattern blueYellowH = new ImagePattern(new Image("images/stomach/blue&yellow1.png"));
    private final ImagePattern blueYellowV = new ImagePattern(new Image("images/stomach/blue&yellow2.png"));
    private final ImagePattern yellowBlueH = new ImagePattern(new Image("images/stomach/blue&yellow3.png"));
    private final ImagePattern yellowBlueV = new ImagePattern(new Image("images/stomach/blue&yellow4.png"));

    public BlueYellowCapsule(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
