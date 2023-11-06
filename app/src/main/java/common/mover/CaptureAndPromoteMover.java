package common.mover;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Piece;
import common.game.Position;
import common.move.Movement;
import common.piece.PieceBuilder;
import common.result.MoveResult;
import common.result.Result;
import common.validator.interfaces.PieceMover;

import java.util.List;
import java.util.Optional;

public class CaptureAndPromoteMover implements PieceMover {

    private final List<PieceMover> pieceMover;
    private final PieceBuilder pieceBuilder;

    public CaptureAndPromoteMover(List<PieceMover> pieceMover, PieceBuilder pieceBuilder) {
        this.pieceMover = pieceMover;
        this.pieceBuilder = pieceBuilder;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        for (PieceMover p: pieceMover){
            if (!p.isValid(movement,board)){
                return false;
            }
        }
        return true;
    }
    //Assuming that first movement is capture and second is promotion...
    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
        Result<Board,Boolean> result=pieceMover.get(0).move(movement,board);
        if (result.value().get()){
            Board boardClone=result.key().copy();
            Position finalPosition= movement.getTo().copy();
            Piece piece= getPiece(boardClone, finalPosition);
            String pieceId=piece.getId();
            Color playerColor=piece.getColor();
            Piece piecePromoted=pieceBuilder.id(pieceId).color(playerColor).build();
            boardClone.getPieces().remove(finalPosition);
            boardClone.getPieces().put(finalPosition,piecePromoted);
            return new MoveResult<>(boardClone, Optional.of(true));

        }
        return new MoveResult<>(board,Optional.of(false));
    }

    private Piece getPiece(Board boardClone, Position finalPosition) {
        return boardClone.getPieces().get(finalPosition);
    }
}