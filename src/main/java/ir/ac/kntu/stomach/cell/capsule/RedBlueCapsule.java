package ir.ac.kntu.stomach.cell.capsule;

import ir.ac.kntu.stomach.Stomach;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RedBlueCapsule extends Capsule {
    private final ImagePattern redBlueH = new ImagePattern(new Image("images/stomach/red&blue1.png"));
    private final ImagePattern redBlueV = new ImagePattern(new Image("images/stomach/red&blue2.png"));
    private final ImagePattern blueRedH = new ImagePattern(new Image("images/stomach/red&blue3.png"));
    private final ImagePattern blueRedV = new ImagePattern(new Image("images/stomach/red&blue4.png"));

    public RedBlueCapsule(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
