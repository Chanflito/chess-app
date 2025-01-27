package validator;

import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

public class IncrementValidator implements MovementValidator {
    private final int increments;

    public IncrementValidator(int increments) {
        this.increments = increments;
    }
    //Checks if the movement is valid with increments.
    @Override
    public boolean isValid(Movement movement, Board board) {
        return Math.abs(movement.getTo().x() - movement.getFrom().x()) <= increments
                && Math.abs(movement.getTo().y() - movement.getFrom().y()) <= increments;
    }
}
