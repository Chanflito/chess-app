package validator.interfaces;

import board.interfaces.Board;
import exception.InvalidMovementException;
import piece.Movement;

public interface MovementValidator {
    boolean isValid(Movement movement, Board board);
}
