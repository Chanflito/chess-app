package common.board;

import common.game.Position;
import common.board.interfaces.Board;
import common.game.Piece;

import java.util.*;

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
    public int getSizeOfRows() {
        return rows;
    }

    @Override
    public int getSizeOfColumns() {
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
        return new RectangularBoard(rows,columns,copyMap(this.pieces),copyHistory());
    }

    private Map<Position,Piece> copyMap(Map<Position,Piece> map){
        Map<Position, Piece> newPieces=new HashMap<>();
        for (Map.Entry<Position,Piece> entry: map.entrySet()){
            Position position=entry.getKey().copy();
            Piece piece= entry.getValue().copy();
            newPieces.put(position,piece);
        }
        return newPieces;
    }

    private List<Map<Position,Piece>> copyHistory(){
        List<Map<Position, Piece>> newHistory=new ArrayList<>();
        for (Map<Position, Piece> historyBoard : historyBoards) {
            newHistory.add(copyMap(historyBoard));
        }
        return newHistory;
    }
}
