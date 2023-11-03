package common.board.interfaces;

import common.game.Position;
import common.game.Piece;

import java.util.List;
import java.util.Map;

public interface Board{
    int getSizeOfRows();
    int getSizeOfColumns();
    Map<Position, Piece> getPieces();

    List<Map<Position,Piece>> getHistory();

    Board copy();
}
