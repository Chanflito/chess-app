package common.mover;

import common.result.MoveResult;
import common.result.Result;
import common.board.interfaces.Board;
import common.game.Piece;
import common.game.Position;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;


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
        Piece pieceToMove=boardClone.getPieces().get(movement.getFrom());
        boardClone.getPieces().remove(movement.getFrom());
        boardClone.getPieces().put(movement.getTo().copy(),pieceToMove);
        return new MoveResult<>(boardClone,true);
    }

    private boolean checkValidators(Movement movement,Board board){
        return orValidator.isValid(movement,board) && ruleValidator.isValid(movement,board);
    }
}
