package common.game;

import common.enums.Color;
import chess.piece.PieceType;
import common.move.MoveHandler;
import chess.piece.interfaces.PieceBuilder;

public class PieceDirector {
    private final PieceBuilder builder;
    private int id;
    public PieceDirector(PieceBuilder builder) {
        this.builder = builder;
    }

    public Piece createPawn(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.PAWN).movement(moveHandler).id(Integer.toString(id++)).build();
    }

    public Piece createKing(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.KING).movement(moveHandler).id(Integer.toString(id++)).build();
    }


    public Piece createQueen(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.QUEEN).id(Integer.toString(id++))
                .movement(moveHandler).build();
    }


    public Piece createRook(Color color,MoveHandler moveHandler){
        return builder.color(color).type(PieceType.ROOK).id(Integer.toString(id++)).movement(moveHandler)
                .build();
    }

    public Piece createBishop(Color color,MoveHandler moveHandler){
        return builder.color(color).id(Integer.toString(id++))
                .movement(moveHandler).type(PieceType.BISHOP).build();
    }

    public Piece createKnight(Color color,MoveHandler moveHandler){
        return builder.color(color).id(Integer.toString(id++))
                .movement(moveHandler).type(PieceType.KNIGHT).build();
    }
    public Piece createArchBishop(Color color,MoveHandler moveHandler){
        return builder.color(color).id(Integer.toString(id++))
                .movement(moveHandler).type(PieceType.ARCHBISHOP).build();
    }
    public Piece createChancellor(Color color,MoveHandler moveHandler){
        return builder.color(color).id(Integer.toString(id++))
                .movement(moveHandler).type(PieceType.CHANCELLOR).build();
    }
}