package ir.ac.kntu.model.gamepage.stomach.cell.pill;

import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.SingleCell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class BluePill extends SingleCell {
    private final ImagePattern bluePill = new ImagePattern(new Image("images/stomach/bluePill.png"));

    public BluePill(Stomach stomach, int rowIndex, int columnIndex) {
        super(stomach, rowIndex, columnIndex);
        super.getStomach().getCells()[rowIndex][columnIndex] = this;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(bluePill);
        gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                CELL_DIMENSIONS, CELL_DIMENSIONS);
    }

    @Override
    public void move() {
        if (super.getRowIndex() >= super.getStomach().getCells().length - 1) {
            return;
        }
        if (super.getStomach().getCells()[super.getRowIndex() + 1][super.getColumnIndex()] != null) {
            return;
        }

        super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = null;
        super.setRowIndex(super.getRowIndex() + 1);
        super.getStomach().getCells()[super.getRowIndex()][super.getColumnIndex()] = this;
    }
}
