package ir.ac.kntu.model.gamepage.rightobjects;

import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.model.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RightBoard implements Drawable {
    private int startX;

    private int startY;

    private int level;

    private GameSpeed speed;

    private int viruses;

    public RightBoard(int startX, int startY, int level, GameSpeed speed, int viruses) {
        this.startX = startX;
        this.startY = startY;
        this.level = level;
        this.speed = speed;
        this.viruses = viruses;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Paint.valueOf("red"));
        gc.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 24));

        gc.fillText("LEVEL", startX, startY);
        gc.fillText(String.valueOf(level), startX, startY + 30);

        gc.fillText("SPEED", startX, startY + 80);
        gc.fillText(speed.name(), startX, startY + 110);

        gc.fillText("VIRUS", startX, startY + 160);
        gc.fillText(String.valueOf(viruses), startX, startY + 190);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setViruses(int viruses) {
        this.viruses = viruses;
    }
}
