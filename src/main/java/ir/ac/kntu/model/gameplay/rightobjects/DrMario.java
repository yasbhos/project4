package ir.ac.kntu.model.gameplay.rightobjects;

import ir.ac.kntu.model.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DrMario implements Drawable {
    private final ImagePattern docWaiting1 = new ImagePattern(new Image("images/doctor/docWaiting1.png"));

    private final ImagePattern docWaiting2 = new ImagePattern(new Image("images/doctor/docWaiting2.png"));

    private final ImagePattern docWaiting3 = new ImagePattern(new Image("images/doctor/docWaiting3.png"));

    private final ImagePattern docThrows = new ImagePattern(new Image("images/doctor/docThrows.png"));

    private final ImagePattern docFailure = new ImagePattern(new Image("images/doctor/docFailure.png"));

    public enum Status {
        DOC_WAITING1,
        DOC_WAITING2,
        DOC_WAITING3,
        DOC_THROWS,
        DOC_FAILURE
    }

    private int startX;

    private int startY;

    private Status status;

    public DrMario(int startX, int startY, Status status) {
        this.startX = startX;
        this.startY = startY;
        this.status = status;
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (status) {
            case DOC_WAITING1 -> {
                gc.setFill(docWaiting1);
                gc.fillRect(startX, startY, docWaiting1.getImage().getWidth() * 3, docWaiting1.getImage().getHeight() * 3);
            }
            case DOC_WAITING2 -> {
                gc.setFill(docWaiting2);
                gc.fillRect(startX, startY, docWaiting2.getImage().getWidth() * 3, docWaiting2.getImage().getHeight() * 3);
            }
            case DOC_WAITING3 -> {
                gc.setFill(docWaiting3);
                gc.fillRect(startX, startY, docWaiting3.getImage().getWidth() * 3, docWaiting3.getImage().getHeight() * 3);
            }
            case DOC_THROWS -> {
                gc.setFill(docThrows);
                gc.fillRect(startX, startY, docThrows.getImage().getWidth() * 3, docThrows.getImage().getHeight() * 3);
            }
            case DOC_FAILURE -> {
                gc.setFill(docFailure);
                gc.fillRect(startX, startY, docFailure.getImage().getWidth() * 3, docFailure.getImage().getHeight() * 3);
            }
            default -> {
            }
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
