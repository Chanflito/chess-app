package chess.piece.mover;

import chess.board.Position;
import common.board.interfaces.Board;
import chess.director.MoveHandlerDirector;
import common.enums.Color;
import common.enums.PieceType;
import chess.result.MoveResult;
import chess.result.Result;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;
import chess.piece.Movement;
import chess.piece.Piece;
import chess.piece.interfaces.MoveHandler;

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
            MoveHandlerDirector moveHandlerDirector =new MoveHandlerDirector();
            MoveHandler moveHandler= moveHandlerDirector.createQueenMovement();
            Piece piece=new Piece(playerColor, PieceType.QUEEN,moveHandler,id);
            boardClone.getPieces().put(finalPosition,piece);
            return new MoveResult<>(boardClone,true);
    }

}
