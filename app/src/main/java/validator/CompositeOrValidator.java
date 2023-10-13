package validator;

import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

import java.util.List;

public class CompositeOrValidator implements MovementValidator {
    private final List<MovementValidator> validators;


    public CompositeOrValidator(List<MovementValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        for (MovementValidator m : validators) {
            if (m.isValid(movement, board)) {
                return true;
            }
        }
        return false;
    }
}
