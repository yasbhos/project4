package ir.ac.kntu.stomach.cell.virus;

import ir.ac.kntu.stomach.Stomach;
import ir.ac.kntu.stomach.cell.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RedVirus extends Cell {
    private final ImagePattern redVirus1 = new ImagePattern(new Image("images/stomach/red-virus1.png"));
    private final ImagePattern redVirus2 = new ImagePattern(new Image("images/stomach/red-virus2.png"));

    public RedVirus(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
