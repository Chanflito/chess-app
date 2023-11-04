package chess.board;

import common.board.RectangularBoard;
import common.board.interfaces.Board;
import common.board.interfaces.BoardBuilder;
import chess.director.MoveHandlerDirector;
import common.game.PieceDirector;
import common.enums.Color;
import common.game.Piece;
import common.piece.PieceCreator;
import common.game.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassicBoardBuilder implements BoardBuilder {
    private int rows;
    private int columns;


    private ClassicBoardBuilder(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }
    public ClassicBoardBuilder(){}

    @Override
    public BoardBuilder boardSize(int rows, int columns) {
        return new ClassicBoardBuilder(rows,columns);
    }

    @Override
    public Board build() {
        Map<Position, Piece> pieces=new HashMap<>();
        List<Map<Position,Piece>> historyBoards=new ArrayList<>();
        Board board=new RectangularBoard(rows,columns,pieces,historyBoards);
        PieceDirector pieceDirector=new PieceDirector(new PieceCreator());
        MoveHandlerDirector moveHandlerDirector = new MoveHandlerDirector();
        assignPawnsIntoBoard(columns,pieces, moveHandlerDirector,pieceDirector);
        assignRooksIntoClassicBoard(pieces,pieceDirector, moveHandlerDirector);
        assignKnightsIntoClassicBoard(pieces,pieceDirector, moveHandlerDirector);
        assignBishopsIntoClassicBoard(pieces,pieceDirector, moveHandlerDirector);
        assignQueensIntoClassicBoard(pieces,pieceDirector, moveHandlerDirector);
        assignKingsIntoClassicBoard(pieces,pieceDirector, moveHandlerDirector);
        historyBoards.add(pieces);
        return board;
    }

    private void assignKingsIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveHandlerDirector moveHandlerDirector) {
        position.put(new Position(4,0), director.createKing(Color.WHITE, moveHandlerDirector.createKingMovement()));
        position.put(new Position(4,7), director.createKing(Color.BLACK, moveHandlerDirector.createKingMovement()));
    }

    private void assignQueensIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveHandlerDirector moveHandlerDirector) {
        position.put(new Position(3,0), director.createQueen(Color.WHITE, moveHandlerDirector.createQueenMovement()));
        position.put(new Position(3,7), director.createQueen(Color.BLACK, moveHandlerDirector.createQueenMovement()));
    }

    private void assignBishopsIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveHandlerDirector moveHandlerDirector){
        position.put(new Position(2,0), director.createBishop(Color.WHITE, moveHandlerDirector.createBishopMovement()));
        position.put(new Position(5,0), director.createBishop(Color.WHITE, moveHandlerDirector.createBishopMovement()));
        position.put(new Position(2,7), director.createBishop(Color.BLACK, moveHandlerDirector.createBishopMovement()));
        position.put(new Position(5,7), director.createBishop(Color.BLACK, moveHandlerDirector.createBishopMovement()));
    }

    private  void assignKnightsIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveHandlerDirector moveHandlerDirector) {
        position.put(new Position(1,0), director.createKnight(Color.WHITE, moveHandlerDirector.createKnightMovement()));
        position.put(new Position(6,0), director.createKnight(Color.WHITE, moveHandlerDirector.createKnightMovement()));
        position.put(new Position(1,7), director.createKnight(Color.BLACK, moveHandlerDirector.createKnightMovement()));
        position.put(new Position(6,7), director.createKnight(Color.BLACK, moveHandlerDirector.createKnightMovement()));
    }

    private  void assignRooksIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveHandlerDirector moveHandlerDirector) {
        position.put(new Position(0,0), director.createRook(Color.WHITE, moveHandlerDirector.createRookMovement()));
        position.put(new Position(7,0), director.createRook(Color.WHITE, moveHandlerDirector.createRookMovement()));
        position.put(new Position(0,7), director.createRook(Color.BLACK, moveHandlerDirector.createRookMovement()));
        position.put(new Position(7,7), director.createRook(Color.BLACK, moveHandlerDirector.createRookMovement()));
    }

    private  void assignPawnsIntoBoard(int columns, Map<Position, Piece> position, MoveHandlerDirector moveHandlerDirector, PieceDirector director) {
        for(int i = 0; i< columns; i++){
            position.put(new Position(i,1), director.createPawn(Color.WHITE, moveHandlerDirector.createPawnMovement(Color.WHITE)));
            position.put(new Position(i,6), director.createPawn(Color.BLACK, moveHandlerDirector.createPawnMovement(Color.BLACK)));
        }
    }
}
