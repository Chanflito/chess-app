package validator;

import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

public class InBoardValidator implements MovementValidator {
    //Checks any posibility of the movement to be out of the board.
    @Override
    public boolean isValid(Movement movement, Board board) {
        int rows= board.getRows();
        int columns= board.getColumns();
        if (movement.getTo().x() < 0 || movement.getTo().x() >= rows) {
            return false;
        } else if (movement.getTo().y() < 0 || movement.getTo().y() >= columns) {
            return false;
        } else if (movement.getFrom().x() < 0 || movement.getFrom().x() >= rows) {
            return false;
        }
        return movement.getFrom().y() >= 0 && movement.getFrom().y() < columns;
    }
}
