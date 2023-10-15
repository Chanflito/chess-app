package validator;

import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

public class CheckValidator implements MovementValidator {

    @Override
    public boolean isValid(Movement movement, Board board) {
        return false;
    }
}
