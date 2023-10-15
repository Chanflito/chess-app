package validator.interfaces;

import board.interfaces.Board;
import piece.Movement;
import piece.Piece;

import java.util.Optional;

public interface MovementValidator {
    boolean isValid(Movement movement, Board board);
}
