package common.validator.interfaces;

import common.board.interfaces.Board;
import chess.piece.Movement;

public interface MovementValidator {
    boolean isValid(Movement movement, Board board);
}
