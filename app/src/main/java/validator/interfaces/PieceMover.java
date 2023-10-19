package validator.interfaces;

import board.interfaces.Board;
import piece.Movement;
import result.Result;

public interface PieceMover extends MovementValidator {
    Result<Board,Boolean> move(Movement movement, Board board);
}
