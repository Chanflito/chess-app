package common.validator.validators;

import common.board.interfaces.Board;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

public class CompositeXorValidator implements MovementValidator {
    private final MovementValidator validatorThatShouldBeFalse;
    private final MovementValidator validatorThatShouldBeTrue;

    public CompositeXorValidator(MovementValidator validatorThatShouldBeFalse, MovementValidator validatorThatShouldBeTrue) {
        this.validatorThatShouldBeFalse = validatorThatShouldBeFalse;
        this.validatorThatShouldBeTrue = validatorThatShouldBeTrue;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (!validatorThatShouldBeFalse.isValid(movement, board)) {
            return validatorThatShouldBeTrue.isValid(movement, board);
        }
        return false;
    }
}
