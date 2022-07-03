package ir.ac.kntu.stomach.cell.virus;

import ir.ac.kntu.stomach.Stomach;
import ir.ac.kntu.stomach.cell.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class YellowVirus extends Cell {
    private final ImagePattern yellowVirus1 = new ImagePattern(new Image("images/stomach/yellow-virus1.png"));
    private final ImagePattern yellowVirus2 = new ImagePattern(new Image("images/stomach/yellow-virus2.png"));

    public YellowVirus(Stomach stomach) {
        super(stomach);
    }

    @Override
    public void draw(GraphicsContext gc, int x, int y) {

    }

    @Override
    public void move() {

    }
}
