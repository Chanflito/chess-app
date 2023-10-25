package checkers;

import chess.board.Position;
import chess.board.RectangularBoard;
import chess.director.MoveHandlerDirector;
import chess.director.PieceDirector;
import chess.piece.Piece;
import chess.piece.PieceCreator;
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


    private void assignPawnsIntoBoard(Map<Position, Piece> position,
                                      CheckersMoveHandlerDirector moveHandlerDirector, PieceDirector director) {
        position.put(new Position(0, 1), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(1, 0), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(1, 0), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(1, 2), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(3, 2), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(5, 2), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(7, 2), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(2, 1), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(3, 0), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(4, 1), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(5, 0), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(6, 1), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(7, 0), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
        position.put(new Position(1, 7), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(0, 6), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(2, 6), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(1, 5), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(3, 5), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(4, 6), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(3, 7), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(5, 7), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(5, 5), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(6, 6), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(7, 5), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        position.put(new Position(7, 7), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
    }
}
