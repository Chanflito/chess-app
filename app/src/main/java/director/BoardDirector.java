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
        PieceDirector director=new PieceDirector();
        PieceCreator pieceCreator=new PieceCreator();
        List<Map<Position,Piece>> history=new ArrayList<>();
        for(int i=0;i<columns;i++){
            position.put(new Position(i,1),director.createPawn(pieceCreator,Color.WHITE));
            director.createPawn(pieceCreator,Color.BLACK);
            position.put(new Position(i,6),director.createPawn(pieceCreator,Color.BLACK));
        }
        position.put(new Position(0,0),director.createRook(pieceCreator,Color.WHITE,MoveHandlerDirector.createRookMovement()));
        position.put(new Position(7,0),director.createRook(pieceCreator,Color.WHITE,MoveHandlerDirector.createRookMovement()));
        position.put(new Position(0,7),director.createRook(pieceCreator,Color.BLACK,MoveHandlerDirector.createRookMovement()));
        position.put(new Position(7,7),director.createRook(pieceCreator,Color.BLACK,MoveHandlerDirector.createRookMovement()));


        position.put(new Position(1,0),director.createKnight(pieceCreator,Color.WHITE,MoveHandlerDirector.setKnightMovement()));
        position.put(new Position(6,0),director.createKnight(pieceCreator,Color.WHITE,MoveHandlerDirector.setKnightMovement()));

        position.put(new Position(1,7),director.createKnight(pieceCreator,Color.BLACK,MoveHandlerDirector.setKnightMovement()));
        position.put(new Position(6,7), director.createKnight(pieceCreator,Color.BLACK,MoveHandlerDirector.setKnightMovement()));

        position.put(new Position(2,0),director.createBishop(pieceCreator,Color.WHITE,MoveHandlerDirector.setBishopMovement()));
        position.put(new Position(5,0),director.createBishop(pieceCreator,Color.WHITE,MoveHandlerDirector.setBishopMovement()));

        position.put(new Position(2,7),director.createBishop(pieceCreator,Color.BLACK,MoveHandlerDirector.setBishopMovement()));
        position.put(new Position(5,7),director.createBishop(pieceCreator,Color.BLACK,MoveHandlerDirector.setBishopMovement()));

        position.put(new Position(3,0),director.createQueen(pieceCreator,Color.WHITE));
        position.put(new Position(3,7),director.createQueen(pieceCreator,Color.BLACK));


        position.put(new Position(4,0),director.createKing(pieceCreator,Color.WHITE));

        position.put(new Position(4,7),director.createKing(pieceCreator,Color.BLACK));
        history.add(position);
        return new RectangularBoard(columns,rows,position,history);
    }


}
