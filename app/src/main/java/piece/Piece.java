package piece;

import enums.Color;
import enums.TypePiece;
import piece.interfaces.MoveHandler;

import java.util.Objects;

public class Piece{
    private final Color color;
    private final TypePiece type;

    private final MoveHandler moveHandler;

    private final String id;

    protected Piece(Color color, TypePiece type, MoveHandler moveHandler, String id) {
        this.color = color;
        this.type = type;
        this.moveHandler = moveHandler;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }
    public TypePiece getType() {
        return type;
    }

    public MoveHandler getMoveHandler() {
        return moveHandler;
    }

    public String getId(){
        return this.id;
    }

    public Piece copy(){
        return new Piece(this.color,this.type,this.moveHandler,this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && piece.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, id);
    }
}
