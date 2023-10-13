package board.interfaces;

import board.Position;
import piece.Piece;

import java.util.List;
import java.util.Map;

public interface Board{
    int getRows();
    int getColumns();
    Map<Position, Piece> getPieces();

    List<Map<Position,Piece>> getHistory();

    Board copy();
}
