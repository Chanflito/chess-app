package checkers.helper;

import common.board.interfaces.Board;
import common.game.Piece;
import common.game.Position;

import java.util.List;
import java.util.Map;

public class TurnHelper {

    private final Position position;

    private final Board board;


    public TurnHelper(Position position, Board board) {
        this.position = position;
        this.board = board;
    }
    //No voy a cambiar de turno si antes capture previamente y ahora puedo capturar una pieza. (en 4 direcciones posibles).
    public boolean notChangeTurn(){
        if (checkPreviousCapture()){
            return checkPotentialCapture();
        }
        return false;
    }

    private boolean checkPreviousCapture(){
        Board boardClone=board.copy();
        List<Map<Position, Piece>> history=boardClone.getHistory();
        int size= history.size();
        Position positionClone=position.copy();
        if (size>=2){
            Map<Position,Piece> currentMap=history.get(size-1);
            Piece piece=currentMap.get(positionClone);
            Map<Position,Piece> previousMap=history.get(size-2);
            Position position=previousMap.entrySet().stream().filter(e->e.getValue().equals(piece)).findFirst().get().getKey();
            return Math.abs(position.x()-positionClone.x())==2 && Math.abs(position.y()-positionClone.y())==2;
        }
        return false;
    }

    private boolean checkPotentialCapture(){
       List<Position> directions= potentialPositionsToCapture();
       Position positionClone=position.copy();
       for (Position p:directions){
           Position evaluate=new Position(positionClone.x()+p.x(),positionClone.y()+p.y());
           if (!board.getPieces().containsKey(evaluate)){
               Position middlePosition= new Position((evaluate.x()+positionClone.x())/2,(evaluate.y()+ positionClone.y())/2);
               if (isEnemyPiece(middlePosition,board)) return true;
           }
       }
       return false;
    }

    private static List<Position> potentialPositionsToCapture(){
        return List.of(new Position(-2,-2),new Position(-2,2),new Position(2,-2),new Position(2,2));
    }

    private boolean isEnemyPiece(Position position, Board board){
        return board.getPieces().containsKey(position) && board.getPieces().get(position).getColor()!=board.getPieces().get(this.position).getColor();
    }
}
