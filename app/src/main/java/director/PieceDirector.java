package director;

import enums.Color;
import enums.PieceType;
import piece.Piece;
import piece.interfaces.MoveHandler;
import piece.interfaces.PieceBuilder;

//TODO CHANGE builder, no pasarselo como paremetro.
public class PieceDirector {
    private final PieceBuilder builder;
    private int id;
    public PieceDirector(PieceBuilder builder) {
        this.builder = builder;
    }

    Piece createPawn(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.PAWN).movement(moveHandler).id(Integer.toString(id++)).getResult();
    }

    Piece createKing(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.KING).movement(moveHandler).id(Integer.toString(id++)).getResult();
    }


     Piece createQueen(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.QUEEN).id(Integer.toString(id++))
                .movement(moveHandler).getResult();
    }


    Piece createRook(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.ROOK).id(Integer.toString(id++)).movement(moveHandler)
                .getResult();
    }

    Piece createBishop(Color color,MoveHandler moveHandler){
        return builder.color(color).id(Integer.toString(id++))
                .movement(moveHandler).type(PieceType.BISHOP).getResult();
    }

    Piece createKnight(Color color,MoveHandler moveHandler){
        return builder.color(color).id(Integer.toString(id++))
                .movement(moveHandler).type(PieceType.KNIGHT).getResult();
    }

}
