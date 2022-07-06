package ir.ac.kntu.model.titlescreen;

import ir.ac.kntu.model.Drawable;
import ir.ac.kntu.model.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Heart implements Drawable, Movable {
    private final ImagePattern shaking1 = new ImagePattern(new Image("images/titleScreen/heart.png"));

    private int startX;

    private int startY;

    public Heart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(shaking1);
        gc.fillRect(startX, startY, 30, 30);
    }

    @Override
    public void move() {

    }
}
