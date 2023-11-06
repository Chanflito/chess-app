package common.mover;

import common.result.MoveResult;
import common.result.Result;
import common.board.interfaces.Board;
import common.move.Movement;
import common.validator.interfaces.PieceMover;

import java.util.List;
import java.util.Optional;

public class CompositeOrMover implements PieceMover {
    private final List<PieceMover> pieceMovers;

    public CompositeOrMover(List<PieceMover> pieceMovers) {
        this.pieceMovers = pieceMovers;
    }
    @Override
    public boolean isValid(Movement movement, Board board) {
        for (PieceMover p: pieceMovers){
            if (p.isValid(movement,board)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
        for (PieceMover p: pieceMovers){
            if (p.isValid(movement,board)){
                Result<Board,Boolean> result=p.move(movement,board);
                if (result.value().get()){
                    return result;
                }
            }
        }
        return new MoveResult<>(board, Optional.of(false));
    }
}
