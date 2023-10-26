package checkers.validator;

import chess.piece.Movement;
import chess.result.Result;
import common.board.interfaces.Board;
import common.validator.interfaces.PieceMover;

public class CaptureMover implements PieceMover {

    @Override
    public boolean isValid(Movement movement, Board board) {
        return false;
    }

    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
        return null;
    }
}
