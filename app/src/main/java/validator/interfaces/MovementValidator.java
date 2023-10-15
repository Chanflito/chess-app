package validator.interfaces;

import board.interfaces.Board;
import piece.Movement;

public interface MovementValidator {
    boolean isValid(Movement movement, Board board);
}
