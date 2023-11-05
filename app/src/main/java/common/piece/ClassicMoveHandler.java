package common.piece;


import common.mover.CompositeOrMover;
import common.result.Result;
import common.board.interfaces.Board;
import common.move.MoveHandler;
import common.move.Movement;

public class ClassicMoveHandler implements MoveHandler {
    private final CompositeOrMover compositeOrMover;

    public ClassicMoveHandler(CompositeOrMover compositeOrMover) {
        this.compositeOrMover = compositeOrMover;
    }

    @Override
    public Result<Board, Boolean> handleMove(Movement movement, Board board) {
        return compositeOrMover.move(movement,board);
    }
}
