package director;

import board.Position;
import board.RectangularBoard;
import board.interfaces.Board;
import enums.Color;
import piece.Piece;
import piece.PieceCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BoardDirector {
    public Board createClassicBoard(int columns, int rows){
        Map<Position, Piece> position=new HashMap<>();
        MoveDirector moveDirector= new MoveDirector();
        PieceDirector pieceDirector=new PieceDirector(new PieceCreator());

        List<Map<Position,Piece>> history=new ArrayList<>();
        assignPawnsIntoBoard(columns,position, moveDirector, pieceDirector);
        assignRooksIntoClassicBoard(position,pieceDirector,moveDirector);

        assignKnightsIntoClassicBoard(position,pieceDirector,moveDirector);

        assignBishopsIntoClassicBoard(position,pieceDirector,moveDirector);

        assignQueensIntoClassicBoard(position,pieceDirector,moveDirector);

        assignKingsIntoClassicBoard(position,pieceDirector,moveDirector);
        history.add(position);
        return new RectangularBoard(columns,rows,position,history);
    }

    public Board createCapablancaBoard(int columns,int rows){
        Map<Position, Piece> position=new HashMap<>();
        MoveDirector moveDirector= new MoveDirector();
        PieceDirector pieceDirector=new PieceDirector(new PieceCreator());
        List<Map<Position,Piece>> history=new ArrayList<>();
        assignArchBishopsIntoCapaBlancaBoard(position,pieceDirector,moveDirector);
        assignChancellorsIntoCapaBlancaBoard(position,pieceDirector,moveDirector);
        assignPawnsIntoBoard(columns,position,moveDirector,pieceDirector);
        assignRooksIntoCapablancaBoard(position,pieceDirector,moveDirector);
        assignKnigthsIntoCapaBlancaBoard(position,pieceDirector,moveDirector);
        assignBishopsIntoCapaBlancaBoard(position,pieceDirector,moveDirector);
        assignQueensIntoCapaBlancaBoard(position,pieceDirector,moveDirector);
        assignKingsIntoCapaBlancaBoard(position,pieceDirector,moveDirector);
        history.add(position);
        return new RectangularBoard(columns,rows,position,history);
    }

    private void assignKingsIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(4,0), director.createKing(Color.WHITE, moveDirector.createKingMovement()));
        position.put(new Position(4,7), director.createKing(Color.BLACK,moveDirector.createKingMovement()));
    }

    private void assignQueensIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(3,0), director.createQueen(Color.WHITE, moveDirector.createQueenMovement()));
        position.put(new Position(3,7), director.createQueen(Color.BLACK,moveDirector.createQueenMovement()));
    }

    private void assignBishopsIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector){
        position.put(new Position(2,0), director.createBishop(Color.WHITE,moveDirector.createBishopMovement()));
        position.put(new Position(5,0), director.createBishop(Color.WHITE,moveDirector.createBishopMovement()));
        position.put(new Position(2,7), director.createBishop(Color.BLACK,moveDirector.createBishopMovement()));
        position.put(new Position(5,7), director.createBishop(Color.BLACK,moveDirector.createBishopMovement()));
    }

    private  void assignKnightsIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(1,0), director.createKnight(Color.WHITE, moveDirector.createKnightMovement()));
        position.put(new Position(6,0), director.createKnight(Color.WHITE,moveDirector.createKnightMovement()));
        position.put(new Position(1,7), director.createKnight(Color.BLACK,moveDirector.createKnightMovement()));
        position.put(new Position(6,7), director.createKnight(Color.BLACK, moveDirector.createKnightMovement()));
    }

    private  void assignRooksIntoClassicBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(0,0), director.createRook(Color.WHITE,moveDirector.createRookMovement()));
        position.put(new Position(7,0), director.createRook(Color.WHITE, moveDirector.createRookMovement()));
        position.put(new Position(0,7), director.createRook(Color.BLACK, moveDirector.createRookMovement()));
        position.put(new Position(7,7), director.createRook(Color.BLACK, moveDirector.createRookMovement()));
    }

    private  void assignPawnsIntoBoard(int columns,Map<Position, Piece> position, MoveDirector moveDirector, PieceDirector director) {
        for(int i = 0; i< columns; i++){
            position.put(new Position(i,1), director.createPawn(Color.WHITE, moveDirector.createPawnMovement(Color.WHITE)));
            position.put(new Position(i,6), director.createPawn(Color.BLACK, moveDirector.createPawnMovement(Color.BLACK)));
        }
    }
    private void assignRooksIntoCapablancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(0,0), director.createRook(Color.WHITE,moveDirector.createRookMovement()));
        position.put(new Position(9,0), director.createRook(Color.WHITE, moveDirector.createRookMovement()));
        position.put(new Position(0,9), director.createRook(Color.BLACK, moveDirector.createRookMovement()));
        position.put(new Position(9,9), director.createRook(Color.BLACK, moveDirector.createRookMovement()));
    }

    private void assignKnigthsIntoCapaBlancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(1,0), director.createKnight(Color.WHITE, moveDirector.createKnightMovement()));
        position.put(new Position(8,0), director.createKnight(Color.WHITE,moveDirector.createKnightMovement()));
        position.put(new Position(1,9), director.createKnight(Color.BLACK,moveDirector.createKnightMovement()));
        position.put(new Position(8,9), director.createKnight(Color.BLACK, moveDirector.createKnightMovement()));
    }

    private void assignBishopsIntoCapaBlancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(3,0), director.createBishop(Color.WHITE,moveDirector.createBishopMovement()));
        position.put(new Position(6,0), director.createBishop(Color.WHITE,moveDirector.createBishopMovement()));
        position.put(new Position(3,9), director.createBishop(Color.BLACK,moveDirector.createBishopMovement()));
        position.put(new Position(6,9), director.createBishop(Color.BLACK,moveDirector.createBishopMovement()));
    }
    private void assignQueensIntoCapaBlancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(4,0), director.createQueen(Color.WHITE, moveDirector.createQueenMovement()));
        position.put(new Position(4,9), director.createQueen(Color.BLACK,moveDirector.createQueenMovement()));
    }

    private void assignKingsIntoCapaBlancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(5,0), director.createKing(Color.WHITE, moveDirector.createKingMovement()));
        position.put(new Position(5,9), director.createKing(Color.BLACK,moveDirector.createKingMovement()));
    }

    private void assignArchBishopsIntoCapaBlancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(2,0), director.createArchBishop(Color.WHITE, moveDirector.createArchBishopMovement()));
        position.put(new Position(7,0), director.createArchBishop(Color.WHITE,moveDirector.createArchBishopMovement()));
        position.put(new Position(2,9), director.createArchBishop(Color.BLACK,moveDirector.createArchBishopMovement()));
        position.put(new Position(7,9), director.createArchBishop(Color.BLACK,moveDirector.createArchBishopMovement()));
    }

    private void assignChancellorsIntoCapaBlancaBoard(Map<Position, Piece> position, PieceDirector director, MoveDirector moveDirector) {
        position.put(new Position(1,0), director.createChancellor(Color.WHITE, moveDirector.createChancellorMovement()));
        position.put(new Position(8,0), director.createChancellor(Color.WHITE,moveDirector.createChancellorMovement()));
        position.put(new Position(1,9), director.createChancellor(Color.BLACK,moveDirector.createChancellorMovement()));
        position.put(new Position(8,9), director.createChancellor(Color.BLACK,moveDirector.createChancellorMovement()));
    }
}
