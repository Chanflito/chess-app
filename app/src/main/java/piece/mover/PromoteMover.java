package piece.mover;

import board.Position;
import board.interfaces.Board;
import director.MoveDirector;
import enums.Color;
import enums.PieceType;
import piece.Movement;
import piece.Piece;
import piece.interfaces.MoveHandler;
import result.MoveResult;
import result.Result;
import utils.Finder;
import validator.UnidirectionalMovementValidator;
import validator.interfaces.MovementValidator;
import validator.interfaces.PieceMover;

import java.util.List;

public class PromoteMover implements PieceMover {
    private final List<MovementValidator> movementValidators;

    public PromoteMover(List<MovementValidator> movementValidators) {
        this.movementValidators = movementValidators;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        for (MovementValidator m: movementValidators){
            if (!m.isValid(movement,board)) return false;
        }
        Piece piece=board.getPieces().get(movement.getFrom());
        Board boardClone=board.copy();
        boardClone.getPieces().remove(movement.getFrom().copy());
        boardClone.getPieces().put(movement.getTo().copy(),piece);
        int column= boardClone.getPieces().get(movement.getTo().copy()).getColor()== Color.WHITE ? 7 : 0;
        return movement.getTo().y() == column;
    }

    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
            Board boardClone=board.copy();
            Position initialPosition=movement.getFrom().copy();
            //Por la UI necesito que tengan el mismo id.
            String id=board.getPieces().get(initialPosition).getId();
            Position finalPosition=movement.getTo().copy();
            Color playerColor=board.getPieces().get(initialPosition).getColor();
            boardClone.getPieces().remove(initialPosition);
            //Por default es queen.
            MoveDirector moveDirector=new MoveDirector();
            MoveHandler moveHandler=moveDirector.createQueenMovement();
            Piece piece=new Piece(playerColor, PieceType.QUEEN,moveHandler,id);
            boardClone.getPieces().put(finalPosition,piece);
            return new MoveResult<>(boardClone,true);
    }

}
