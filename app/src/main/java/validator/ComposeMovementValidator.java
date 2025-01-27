package validator;

import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

public class ComposeMovementValidator implements MovementValidator {
    private final int x;
    private final int y;

    public ComposeMovementValidator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (movement.getTo().x()==movement.getFrom().x()+this.x){
            return movement.getTo().y()==movement.getFrom().y()+this.y;
        }
        return false;
    }
}
