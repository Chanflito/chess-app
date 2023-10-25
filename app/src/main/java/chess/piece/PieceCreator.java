package chess.piece;

import common.enums.Color;
import common.enums.PieceType;
import chess.piece.interfaces.PieceBuilder;
import chess.piece.interfaces.MoveHandler;

public class PieceCreator implements PieceBuilder {
    private Color color;
    private PieceType type;

    private MoveHandler moveHandler;

    private String id;
    public PieceCreator() {
    }

    private PieceCreator(Color color, PieceType type, MoveHandler movements, String id) {
        this.color = color;
        this.type = type;
        this.moveHandler = movements;
        this.id = id;
    }

    @Override
    public PieceCreator color(Color color) {
        return new PieceCreator(color,this.type,this.moveHandler, id);
    }

    @Override
    public PieceCreator type(PieceType pieceType) {
        return new PieceCreator(this.color, pieceType,this.moveHandler,this.id);
    }

    @Override
    public PieceBuilder movement(MoveHandler moveHandler) {
        return new PieceCreator(this.color,this.type, moveHandler,this.id);
    }

    @Override
    public PieceCreator id(String id) {
        return new PieceCreator(this.color,this.type,this.moveHandler,id);
    }

    public Piece build() {
        return new Piece(this.color, this.type,this.moveHandler, id);
    }
}
