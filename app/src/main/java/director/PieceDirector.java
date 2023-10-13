package director;

import enums.Color;
import enums.TypePiece;
import piece.Piece;
import piece.interfaces.PieceBuilder;
import validator.interfaces.MovementValidator;

import java.util.List;
//TODO CHANGE builder, no pasarselo como paremetro.
public class PieceDirector {
    private final PieceBuilder builder;

    public PieceDirector(PieceBuilder builder) {
        this.builder = builder;
    }

    Piece createKing(PieceBuilder builder, Color color){
        return builder.color(color).type(TypePiece.KING).getResult();
    }


     Piece createQueen(PieceBuilder builder,Color color){
        return builder.color(color).type(TypePiece.QUEEN).getResult();
    }


    Piece createRook(PieceBuilder builder,Color color,List<MovementValidator> movements){
        return builder.color(color).movement(movements).type(TypePiece.ROOK).getResult();
    }

    Piece createBishop(PieceBuilder builder,Color color,List<MovementValidator> movements){
        return builder.color(color).type(TypePiece.BISHOP).movement(movements).getResult();
    }

    Piece createKnight(PieceBuilder builder,Color color,List<MovementValidator> movements){
        return builder.color(color).movement(movements).type(TypePiece.KNIGHT).getResult();
    }

    Piece createPawn(PieceBuilder builder,Color color){
        return builder.color(color).type(TypePiece.PAWN).getResult();
    }
}
