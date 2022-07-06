package ir.ac.kntu.model;

import ir.ac.kntu.constant.Color;
import ir.ac.kntu.constant.GlobalConstants;
import ir.ac.kntu.model.gameplay.stomach.Stomach;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.BluePill;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.RedPill;
import ir.ac.kntu.model.gameplay.stomach.cell.pill.YellowPill;

import java.security.SecureRandom;

public class SelfMedication implements Runnable {
    private final SecureRandom secureRandom;

    private Stomach stomach;

    public SelfMedication(Stomach stomach) {
        secureRandom = new SecureRandom();
        this.stomach = stomach;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(15000);
            if (secureRandom.nextDouble() * 100 <= GlobalConstants.SELF_MEDICATION_POSSIBILITY) {
                takeAPill();
            }
        } catch (InterruptedException e) {
            System.out.println("(SelfMedication::run) The thread is interrupted by somebody");
            System.out.println(e.getMessage());
        }
        run();
    }

    private void takeAPill() {
        Color[] colors = Color.values();
        int random = secureRandom.nextInt(colors.length);
        int rowIndex = secureRandom.nextInt(stomach.getCells().length);
        int columnIndex = secureRandom.nextInt(stomach.getCells()[0].length);

        if (stomach.getCells()[rowIndex][columnIndex] != null) {
            takeAPill();
        }

        switch (colors[random]) {
            case RED -> {
                stomach.getCells()[rowIndex][columnIndex] = new RedPill(stomach, rowIndex, columnIndex);
                stomach.addObject(stomach.getCells()[rowIndex][columnIndex]);
            }
            case BLUE -> {
                stomach.getCells()[rowIndex][columnIndex] = new BluePill(stomach, rowIndex, columnIndex);
                stomach.addObject(stomach.getCells()[rowIndex][columnIndex]);
            }
            case YELLOW -> {
                stomach.getCells()[rowIndex][columnIndex] = new YellowPill(stomach, rowIndex, columnIndex);
                stomach.addObject(stomach.getCells()[rowIndex][columnIndex]);
            }
            default -> {
            }
        }
    }
}
