package common.piece;

import common.enums.Color;
import common.enums.PieceType;
import common.game.Piece;
import common.move.MoveHandler;

public interface PieceBuilder {
    PieceBuilder color(Color color);
    PieceBuilder type(PieceType pieceType);
    PieceBuilder movement(MoveHandler moveHandler);

    PieceBuilder id(String id);
    Piece build();
}
