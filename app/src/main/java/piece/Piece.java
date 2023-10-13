package piece;

import enums.Color;
import enums.TypePiece;
import validator.interfaces.MovementValidator;

import java.util.List;
import java.util.Objects;

public class Piece{
    private final Color color;
    private final TypePiece type;

    private final List<MovementValidator> movements;

    private final String id;

    protected Piece(Color color, TypePiece type, List<MovementValidator> movements, String id) {
        this.color = color;
        this.type = type;
        this.movements = movements;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }
    public TypePiece getType() {
        return type;
    }

    public List<MovementValidator> getMovements() {
        return movements;
    }

    public String id(){
        return this.id;
    }

    public Piece copy(){
        return new Piece(this.color,this.type,this.movements,this.id);
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
