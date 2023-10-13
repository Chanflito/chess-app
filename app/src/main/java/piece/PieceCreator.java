package piece;

import enums.Color;
import enums.TypePiece;
import piece.interfaces.MoveHandler;
import piece.interfaces.PieceBuilder;
import validator.interfaces.MovementValidator;

import java.util.List;

public class PieceCreator implements PieceBuilder {
    private Color color;
    private TypePiece type;

    private MoveHandler moveHandler;

    private String name;
    public PieceCreator() {
    }

    private PieceCreator(Color color, TypePiece type, MoveHandler movements, String name) {
        this.color = color;
        this.type = type;
        this.moveHandler = movements;
        this.name=name;
    }

    @Override
    public PieceCreator color(Color color) {
        return new PieceCreator(color,this.type,this.moveHandler,name);
    }

    @Override
    public PieceCreator type(TypePiece typePiece) {
        return new PieceCreator(this.color,typePiece,this.moveHandler,this.name);
    }

    @Override
    public PieceBuilder movement(MoveHandler moveHandler) {
        return new PieceCreator(this.color,this.type, moveHandler,this.name);
    }

    @Override
    public PieceCreator id(String name) {
        return new PieceCreator(this.color,this.type,this.moveHandler,this.name);
    }

    public Piece getResult() {
        return new Piece(this.color, this.type,this.moveHandler,name);
    }
}
