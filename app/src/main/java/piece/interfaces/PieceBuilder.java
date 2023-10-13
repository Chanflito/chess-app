package piece.interfaces;

import enums.Color;
import enums.TypePiece;
import piece.Piece;
import validator.interfaces.MovementValidator;

import java.util.List;

public interface PieceBuilder {
    PieceBuilder setColor(Color color);
    PieceBuilder setType(TypePiece typePiece);
    PieceBuilder setMovement(List<MovementValidator> movement);

    PieceBuilder setName(String name);
    Piece getResult();
}
