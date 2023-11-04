package chess.piece;


import chess.piece.mover.CompositeMover;
import common.result.Result;
import common.board.interfaces.Board;
import common.move.MoveHandler;
import common.move.Movement;

public class ClassicMoveHandler implements MoveHandler {
    private final CompositeMover compositeMover;

    public ClassicMoveHandler(CompositeMover compositeMover) {
        this.compositeMover = compositeMover;
    }

    @Override
    public Result<Board, Boolean> handleMove(Movement movement, Board board) {
        return compositeMover.move(movement,board);
    }
}
