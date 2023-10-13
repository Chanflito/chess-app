package director;

import enums.Color;
import enums.TypePiece;
import piece.Piece;
import piece.interfaces.PieceBuilder;
import validator.interfaces.MovementValidator;

import java.util.List;
//TODO CHANGE builder, no pasarselo como paremetro.
public class PieceDirector {
     Piece createKing(PieceBuilder builder, Color color){
        return builder.setColor(color).setType(TypePiece.KING).getResult();
    }


     Piece createQueen(PieceBuilder builder,Color color){
        return builder.setColor(color).setType(TypePiece.QUEEN).getResult();
    }


    Piece createRook(PieceBuilder builder,Color color,List<MovementValidator> movements){
        return builder.setColor(color).setMovement(movements).setType(TypePiece.ROOK).getResult();
    }

    Piece createBishop(PieceBuilder builder,Color color,List<MovementValidator> movements){
        return builder.setColor(color).setType(TypePiece.BISHOP).setMovement(movements).getResult();
    }

    Piece createKnight(PieceBuilder builder,Color color,List<MovementValidator> movements){
        return builder.setColor(color).setMovement(movements).setType(TypePiece.KNIGHT).getResult();
    }

    Piece createPawn(PieceBuilder builder,Color color){
        return builder.setColor(color).setType(TypePiece.PAWN).getResult();
    }
}
