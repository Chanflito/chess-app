package board;

import board.interfaces.Board;
import piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class RectangularBoard implements Board {
    private final int rows;
    private final int columns;
    private final Map<Position, Piece> pieces;

    private final List<Map<Position,Piece>> historyBoards;

    public RectangularBoard(int rows, int columns, Map<Position, Piece> pieces, List<Map<Position, Piece>> historyBoards) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = pieces;
        this.historyBoards = historyBoards;
    }


    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    @Override
    public List<Map<Position, Piece>> getHistory() {
        return this.historyBoards;
    }

    @Override
    public RectangularBoard copy(){
        Map<Position, Piece> clonePieces = new HashMap<>(this.getPieces());
        return new RectangularBoard(rows,columns,clonePieces,historyBoards);
    }
}
