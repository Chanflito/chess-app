package common.validator.interfaces;

import common.board.interfaces.Board;
import chess.result.Result;
import common.move.Movement;

public interface PieceMover extends MovementValidator {
    Result<Board,Boolean> move(Movement movement, Board board);
}
