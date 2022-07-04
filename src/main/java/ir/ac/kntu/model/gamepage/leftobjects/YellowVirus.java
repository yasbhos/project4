package ir.ac.kntu.model.gamepage.leftobjects;

import ir.ac.kntu.model.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class YellowVirus implements Drawable {
    private final ImagePattern shaking1 = new ImagePattern(new Image("images/viruses/YellowVirusShaking1.png"));

    private final ImagePattern shaking2 = new ImagePattern(new Image("images/viruses/YellowVirusShaking2.png"));

    private final ImagePattern laughing1 = new ImagePattern(new Image("images/viruses/YellowVirusLaughing1.png"));

    private final ImagePattern laughing2 = new ImagePattern(new Image("images/viruses/YellowVirusLaughing2.png"));

    private final ImagePattern die1 = new ImagePattern(new Image("images/viruses/YellowVirusDie1.png"));

    private final ImagePattern die2 = new ImagePattern(new Image("images/viruses/YellowVirusDie2.png"));

    public enum Status {
        SHAKING,
        LAUGHING,
        DIE
    }

    private enum SubStatus {
        ONE,
        TWO
    }

    private int startX;

    private int startY;

    private Status status;

    private SubStatus subStatus;

    public YellowVirus(int startX, int startY, Status status) {
        this.startX = startX;
        this.startY = startY;
        this.status = status;
        this.subStatus = subStatus.ONE;
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (status) {
            case SHAKING -> drawShaking(gc);
            case LAUGHING -> drawLaughing(gc);
            case DIE -> drawDie(gc);
            default -> {
            }
        }
    }

    private void drawShaking(GraphicsContext gc) {
        switch (subStatus) {
            case ONE -> {
                gc.setFill(shaking1);
                gc.fillRect(startX, startY, shaking1.getImage().getWidth() * 3, shaking1.getImage().getHeight() * 3);
                this.subStatus = SubStatus.TWO;
            }
            case TWO -> {
                gc.setFill(shaking2);
                gc.fillRect(startX, startY, shaking2.getImage().getWidth() * 3, shaking2.getImage().getHeight() * 3);
                this.subStatus = SubStatus.ONE;
            }
            default -> {
            }
        }
    }

    private void drawLaughing(GraphicsContext gc) {
        switch (subStatus) {
            case ONE -> {
                gc.setFill(laughing1);
                gc.fillRect(startX, startY, laughing1.getImage().getWidth() * 3, laughing1.getImage().getHeight() * 3);
                this.subStatus = SubStatus.TWO;
            }
            case TWO -> {
                gc.setFill(laughing2);
                gc.fillRect(startX, startY, laughing2.getImage().getWidth() * 3, laughing2.getImage().getHeight() * 3);
                this.subStatus = SubStatus.ONE;
            }
            default -> {
            }
        }
    }

    private void drawDie(GraphicsContext gc) {
        switch (subStatus) {
            case ONE -> {
                gc.setFill(die1);
                gc.fillRect(startX, startY, die1.getImage().getWidth() * 3, die1.getImage().getHeight() * 3);
                this.subStatus = SubStatus.TWO;
            }
            case TWO -> {
                gc.setFill(die2);
                gc.fillRect(startX, startY, die2.getImage().getWidth() * 3, die2.getImage().getHeight() * 3);
                this.subStatus = SubStatus.ONE;
            }
            default -> {
            }
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
