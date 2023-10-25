package chess.piece.interfaces;

import common.enums.Color;
import common.enums.PieceType;
import chess.piece.Piece;

public interface PieceBuilder {
    PieceBuilder color(Color color);
    PieceBuilder type(PieceType pieceType);
    PieceBuilder movement(MoveHandler moveHandler);

    PieceBuilder id(String id);
    Piece build();
}
