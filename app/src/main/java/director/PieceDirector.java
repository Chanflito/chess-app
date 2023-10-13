package director;

import enums.Color;
import enums.TypePiece;
import piece.Piece;
import piece.interfaces.MoveHandler;
import piece.interfaces.PieceBuilder;
import validator.interfaces.MovementValidator;

import java.util.List;
//TODO CHANGE builder, no pasarselo como paremetro.
public class PieceDirector {
    private final PieceBuilder builder;


    public PieceDirector(PieceBuilder builder) {
        this.builder = builder;
    }

    Piece createPawn(Color color,MoveHandler moveHandler,String id){
        return builder.color(color).type(TypePiece.PAWN).movement(moveHandler).id(id).getResult();
    }

    Piece createKing(Color color){
        return builder.color(color).type(TypePiece.KING).getResult();
    }


     Piece createQueen(Color color){
        return builder.color(color).type(TypePiece.QUEEN).getResult();
    }


    Piece createRook(Color color){
        return builder.color(color).type(TypePiece.ROOK).getResult();
    }

    Piece createBishop(Color color){
        return builder.color(color).type(TypePiece.BISHOP).getResult();
    }

    Piece createKnight(Color color){
        return builder.color(color).type(TypePiece.KNIGHT).getResult();
    }

}
