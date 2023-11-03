package chess.piece.mover;

import chess.result.MoveResult;
import chess.result.Result;
import common.board.interfaces.Board;
import common.game.Piece;
import common.game.Position;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;

import java.util.List;
import java.util.Map;


public class DefaultMover implements PieceMover {

    private final MovementValidator orValidator;
    private final MovementValidator ruleValidator;

    public DefaultMover(MovementValidator orValidator, MovementValidator ruleValidator) {
        this.orValidator = orValidator;
        this.ruleValidator = ruleValidator;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        return checkValidators(movement,board);
    }

    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
        Board boardClone= board.copy();
        List<Map<Position, Piece>> history= board.getHistory();
        history.add(boardClone.getPieces());
        Piece pieceToMove=boardClone.getPieces().get(movement.getFrom());
        boardClone.getPieces().remove(movement.getFrom());
        boardClone.getPieces().put(movement.getTo().copy(),pieceToMove);
        return new MoveResult<>(boardClone,true);
    }

    private boolean checkValidators(Movement movement,Board board){
        return orValidator.isValid(movement,board) && ruleValidator.isValid(movement,board);
    }
}
