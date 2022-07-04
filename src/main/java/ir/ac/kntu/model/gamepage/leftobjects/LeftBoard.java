package ir.ac.kntu.model.gamepage.leftobjects;

import ir.ac.kntu.model.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LeftBoard implements Drawable {
    private int startX;

    private int startY;

    private int highScore;

    private int score;

    public LeftBoard(int startX, int startY, int highScore, int score) {
        this.startX = startX;
        this.startY = startY;
        this.highScore = highScore;
        this.score = score;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Paint.valueOf("green"));
        gc.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 24));

        gc.fillText("HIGH SCORE", startX, startY);
        gc.fillText(String.valueOf(highScore), startX, startY + 30);

        gc.fillText("SCORE", startX, startY + 110);
        gc.fillText(String.valueOf(score), startX, startY + 140);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
