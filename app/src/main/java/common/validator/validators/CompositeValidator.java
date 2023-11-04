package common.validator.validators;

import common.board.interfaces.Board;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

public class CompositeValidator implements MovementValidator {
    private final MovementValidator orValidator;
    private final MovementValidator andValidator;

    public CompositeValidator(MovementValidator orValidator, MovementValidator andValidator) {
        this.orValidator = orValidator;
        this.andValidator = andValidator;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        return orValidator.isValid(movement,board) && andValidator.isValid(movement,board);
    }
}
