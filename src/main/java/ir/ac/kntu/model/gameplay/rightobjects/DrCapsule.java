package ir.ac.kntu.model.gameplay.rightobjects;

import ir.ac.kntu.model.gameplay.stomach.Stomach;
import ir.ac.kntu.model.gameplay.stomach.cell.PairCell;
import ir.ac.kntu.model.gameplay.stomach.cell.capsule.*;

import java.security.SecureRandom;

public class DrCapsule {
    private final SecureRandom secureRandom;

    public enum Capsule {
        RED,
        BLUE,
        YELLOW,
        RED_BLUE,
        BLUE_RED,
        RED_YELLOW,
        YELLOW_RED,
        BLUE_YELLOW,
        YELLOW_BLUE
    }

    public DrCapsule() {
        secureRandom = new SecureRandom();
    }

    public PairCell getCapsule(Stomach stomach) {
        Capsule[] capsules = Capsule.values();
        int random = secureRandom.nextInt(capsules.length);

        return switch (capsules[random]) {
            case RED -> new RedCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
            case BLUE -> new BlueCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
            case YELLOW -> new YellowCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
            case RED_BLUE -> new RedBlueCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
            case BLUE_RED -> {
                RedBlueCapsule rb = new RedBlueCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
                rb.setStatus(PairCell.Status.HORIZONTAL_TWO);
                yield rb;
            }
            case RED_YELLOW -> new RedYellowCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
            case YELLOW_RED -> {
                RedYellowCapsule ry = new RedYellowCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
                ry.setStatus(PairCell.Status.HORIZONTAL_TWO);
                yield ry;
            }
            case BLUE_YELLOW -> new BlueYellowCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
            case YELLOW_BLUE -> {
                BlueYellowCapsule by = new BlueYellowCapsule(stomach, 0, stomach.getCells()[0].length / 2 - 1);
                by.setStatus(PairCell.Status.HORIZONTAL_TWO);
                yield by;
            }
        };
    }
}
