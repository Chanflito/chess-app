package piece.interfaces;

import enums.Color;
import enums.TypePiece;
import piece.Piece;
import validator.interfaces.MovementValidator;

import java.util.List;

public interface PieceBuilder {
    PieceBuilder color(Color color);
    PieceBuilder type(TypePiece typePiece);
    PieceBuilder movement(MoveHandler moveHandler);

    PieceBuilder id(String id);
    Piece getResult();
}
