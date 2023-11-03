package common.move;

import common.board.interfaces.Board;
import common.move.Movement;
import chess.result.Result;


public interface MoveHandler {

    Result<Board,Boolean> handleMove(Movement movement, Board board);
}
