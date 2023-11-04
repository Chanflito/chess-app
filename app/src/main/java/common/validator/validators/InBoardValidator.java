package common.validator.validators;

import common.board.interfaces.Board;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

public class InBoardValidator implements MovementValidator {
    //Checks any posibility of the movement to be out of the board.
    @Override
    public boolean isValid(Movement movement, Board board) {
        int rows= board.getSizeOfRows();
        int columns= board.getSizeOfColumns();
        return isOutsideFromBoard(movement, rows, columns);
    }

    private boolean isOutsideFromBoard(Movement movement, int rows, int columns) {
        return movement.getTo().x() < 0 || movement.getTo().x() >= rows ||
                movement.getTo().y() < 0 || movement.getTo().y() >= columns ||
                movement.getFrom().x() < 0 || movement.getFrom().x() >= rows ||
                movement.getFrom().y() < 0 || movement.getFrom().y() >= columns;
    }
}
