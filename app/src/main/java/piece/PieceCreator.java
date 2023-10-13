package piece;

import enums.Color;
import enums.TypePiece;
import piece.interfaces.PieceBuilder;
import validator.interfaces.MovementValidator;

import java.util.List;

public class PieceCreator implements PieceBuilder {
    private Color color;
    private TypePiece type;

    private List<MovementValidator> movements;

    private String name;
    public PieceCreator() {
    }

    private PieceCreator(Color color, TypePiece type, List<MovementValidator> movements,String name) {
        this.color = color;
        this.type = type;
        this.movements = movements;
        this.name=name;
    }

    @Override
    public PieceCreator setColor(Color color) {
        return new PieceCreator(color,this.type,this.movements,name);
    }

    @Override
    public PieceCreator setType(TypePiece typePiece) {
        return new PieceCreator(this.color,typePiece,this.movements,this.name);
    }

    @Override
    public PieceCreator setMovement(List<MovementValidator> movements) {
        return new PieceCreator(this.color,this.type,movements,this.name);
    }

    @Override
    public PieceCreator setName(String name) {
        return new PieceCreator(this.color,this.type,this.movements,this.name);
    }

    public Piece getResult() {
        return new Piece(this.color, this.type,this.movements,name);
    }
}
