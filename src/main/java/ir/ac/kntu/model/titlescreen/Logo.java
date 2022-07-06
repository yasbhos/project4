package ir.ac.kntu.model.titlescreen;

import ir.ac.kntu.model.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Logo implements Drawable {
    private final ImagePattern logo1 = new ImagePattern(new Image("images/titleScreen/logo.png"));

    private final ImagePattern logo2 = new ImagePattern(new Image("images/titleScreen/logo2.png"));

    public enum Status {
        ONE,
        TWO
    }

    private int startX;

    private int startY;

    private Status status;

    public Logo(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.status = Status.ONE;
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (status) {
            case ONE -> {
                gc.setFill(logo1);
                status = Status.TWO;
            }
            case TWO -> {
                gc.setFill(logo2);
                status = Status.ONE;
            }
            default -> {
            }
        }

        gc.fillRect(startX, startY, logo1.getImage().getWidth(), logo2.getImage().getHeight());
    }
}
