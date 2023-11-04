package common.mover;

import common.piece.PieceBuilder;
import common.game.Position;
import common.board.interfaces.Board;
import common.enums.Color;
import common.result.MoveResult;
import common.result.Result;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;
import common.move.Movement;
import common.game.Piece;

public class PromoteMover implements PieceMover {
    private final MovementValidator movementValidator;
    private final PieceBuilder pieceBuilder;
    //Should be pre created with movehandler,piecetype.
    public PromoteMover(MovementValidator movementValidator, PieceBuilder pieceBuilder) {
        this.movementValidator = movementValidator;
        this.pieceBuilder=pieceBuilder;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (!movementValidator.isValid(movement,board)) return false;
        Piece piece=board.getPieces().get(movement.getFrom());
        Board boardClone=board.copy();
        boardClone.getPieces().remove(movement.getFrom().copy());
        boardClone.getPieces().put(movement.getTo().copy(),piece);
        int column= boardClone.getPieces().get(movement.getTo().copy()).getColor()== Color.WHITE ? 7 : 0;
        return movement.getTo().y() == column;
    }

    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
            if (!isValid(movement,board)) return new MoveResult<>(board,false);
            Board boardClone=board.copy();
            Position initialPosition=movement.getFrom().copy();
            //Por la UI necesito que tengan el mismo id.
            String id=board.getPieces().get(initialPosition).getId();
            Position finalPosition=movement.getTo().copy();
            Color playerColor=board.getPieces().get(initialPosition).getColor();
            boardClone.getPieces().remove(initialPosition);
            //Por default es queen.
            Piece piece=pieceBuilder.id(id).color(playerColor).build();
            boardClone.getPieces().put(finalPosition,piece);
            return new MoveResult<>(boardClone,true);
    }

}
