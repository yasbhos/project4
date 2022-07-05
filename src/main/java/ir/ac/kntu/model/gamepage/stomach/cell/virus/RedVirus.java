package ir.ac.kntu.model.gamepage.stomach.cell.virus;

import ir.ac.kntu.model.gamepage.stomach.Stomach;
import ir.ac.kntu.model.gamepage.stomach.cell.SingleCell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static ir.ac.kntu.constant.GlobalConstants.*;

public class RedVirus extends SingleCell {
    private final ImagePattern redVirus1 = new ImagePattern(new Image("images/stomach/red-virus1.png"));

    private final ImagePattern redVirus2 = new ImagePattern(new Image("images/stomach/red-virus2.png"));

    private enum Status {
        ONE,
        TWO
    }

    private Status status;

    public RedVirus(Stomach stomach, int rowIndex, int columnIndex) {
        super(stomach, rowIndex, columnIndex);
        super.getStomach().getCells()[rowIndex][columnIndex] = this;
        this.status = Status.ONE;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (status == Status.ONE) {
            gc.setFill(redVirus1);
            status = Status.TWO;
        } else {
            gc.setFill(redVirus2);
            status = Status.ONE;
        }

        gc.fillRect(STOMACH_START_X + (super.getColumnIndex() * CELL_DIMENSIONS),
                STOMACH_START_Y + (super.getRowIndex() * CELL_DIMENSIONS),
                CELL_DIMENSIONS, CELL_DIMENSIONS);
    }
}
