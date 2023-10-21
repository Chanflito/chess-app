package piece.interfaces;

import enums.Color;
import enums.PieceType;
import piece.Piece;

public interface PieceBuilder {
    PieceBuilder color(Color color);
    PieceBuilder type(PieceType pieceType);
    PieceBuilder movement(MoveHandler moveHandler);

    PieceBuilder id(String id);
    Piece build();
}
