package checkers;

import common.game.Position;
import common.board.RectangularBoard;
import common.game.PieceDirector;
import common.game.Piece;
import common.piece.PieceCreator;
import common.board.interfaces.Board;
import common.board.interfaces.BoardBuilder;
import common.enums.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckersBoardBuilder implements BoardBuilder {
    private  int rows;
    private  int columns;

    public CheckersBoardBuilder() {
    }

    private CheckersBoardBuilder (int rows, int columns){
        this.rows = rows;
        this.columns = columns;
    }
    @Override
    public BoardBuilder boardSize(int rows, int columns) {
        return new CheckersBoardBuilder(rows, columns);
    }

    @Override
    public Board build() {
        Map<Position, Piece> pieces=new HashMap<>();
        List<Map<Position,Piece>> historyBoards=new ArrayList<>();
        PieceDirector pieceDirector=new PieceDirector(new PieceCreator());
        CheckersMoveHandlerDirector movementHandlerDirector=new CheckersMoveHandlerDirector();
        assignPawnsIntoBoard(pieces, movementHandlerDirector, pieceDirector);
        historyBoards.add(pieces);
        return new RectangularBoard(rows,columns,pieces,historyBoards);
    }


    private void assignPawnsIntoBoard(Map<Position, Piece> pieces,
                                      CheckersMoveHandlerDirector moveHandlerDirector, PieceDirector pieceDirector) {
        // Create and place white pawns
        for (int columns = 0; columns < 3; columns++) {
            for (int rows = (columns % 2 == 0) ? 1 : 0; rows < this.columns; rows += 2) {
                pieces.put(new Position(rows,columns), pieceDirector.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
            }
        }

        // Create and place black pawns
        for (int columns = rows - 3; columns < rows; columns++) {
            for (int rows = (columns % 2 == 0) ? 1 : 0; rows < this.columns; rows += 2) {
                pieces.put(new Position(rows, columns), pieceDirector.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
            }
        }
    }
}
