package ir.ac.kntu.stomach.cell.virus;

import ir.ac.kntu.stomach.Stomach;
import ir.ac.kntu.stomach.cell.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class BlueVirus extends Cell {
    private final ImagePattern blueVirus1 = new ImagePattern(new Image("images/stomach/blue-virus1.png"));
    private final ImagePattern blueVirus2 = new ImagePattern(new Image("images/stomach/blue-virus2.png"));

    public BlueVirus(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
