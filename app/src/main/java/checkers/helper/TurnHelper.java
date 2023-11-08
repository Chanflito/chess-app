package checkers.helper;

import common.board.interfaces.Board;
import common.game.Piece;
import common.game.Position;
import common.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TurnHelper {

    public TurnHelper() {
    }
    //No voy a cambiar de turno si antes capture previamente y ahora puedo capturar una pieza. (en 4 direcciones posibles).
    public boolean notChangeTurn(Board board){
        Board boardClone=board.copy();
        List<Map<Position, Piece>> history=boardClone.getHistory();
        int size= history.size();
        return evaluateConditions(history, size, board);
    }

    private boolean evaluateConditions(List<Map<Position, Piece>> history, int size,Board board) {
        if (size >=2){
            Map<Position,Piece> currentMap= history.get(size -1);
            Map<Position,Piece> previousMap= history.get(size -2);
            Optional<Pair<Position,Position>> positionsOfPieceFound=findPositionOfPieceMoved(previousMap,currentMap);
            if (positionsOfPieceFound.isPresent()){
                Pair<Position,Position> positionsOfPiece=positionsOfPieceFound.get();
                if (checkPreviousCapture(positionsOfPiece)){
                    return checkPotentialCapture(getCurrentPosition(positionsOfPiece),board.copy());
                }
            }
        }
        return false;
    }

    private Position getCurrentPosition(Pair<Position, Position> positionsOfPiece) {
        return positionsOfPiece.value();
    }


    private boolean checkPreviousCapture(Pair<Position,Position> positionsOfPiece){
        return Math.abs(positionsOfPiece.key().x()- positionsOfPiece.value().x())==2
                && Math.abs(positionsOfPiece.key().y()- positionsOfPiece.value().y())==2;

    }

    private boolean checkPotentialCapture(Position currentPosition,Board board){
        List<Position> directions= potentialPositionsToCapture();
        Position positionClone=currentPosition.copy();
        for (Position p:directions){
            Position evaluate=new Position(positionClone.x()+p.x(),positionClone.y()+p.y());
            if (!board.getPieces().containsKey(evaluate) && isNotOutOfBounds(evaluate,board)){
                Position middlePosition= new Position((evaluate.x()+positionClone.x())/2,(evaluate.y()+ positionClone.y())/2);
                if (isEnemyPiece(middlePosition,board,currentPosition)) return true;
            }
        }
        return false;
    }

    private Optional<Pair<Position,Position>> findPositionOfPieceMoved(Map<Position,Piece> previousMap, Map<Position,Piece> currentMap){
        for (Map.Entry<Position,Piece> previousMapEntry: previousMap.entrySet()){
            if (!currentMap.containsKey(previousMapEntry.getKey())){// Si no lo encuentra, es porque se pudo haber movido entonces busco la pieza, si existe.
                Piece pieceInPreviousBoard= previousMapEntry.getValue();
                Position previousPosition=previousMapEntry.getKey();
                for (Map.Entry<Position,Piece> currentMapEntry: currentMap.entrySet()){
                    Piece pieceInCurrentBoard= currentMapEntry.getValue();
                    if (pieceInCurrentBoard.equals(pieceInPreviousBoard)){
                        Position lastPosition= currentMapEntry.getKey();
                        return Optional.of(new Pair<>(previousPosition,lastPosition));
                    }
                }
            }
        }
        return Optional.empty();
    }
    private static List<Position> potentialPositionsToCapture(){
        return List.of(new Position(-2,-2),new Position(-2,2),new Position(2,-2),new Position(2,2));
    }

    private boolean isEnemyPiece(Position position, Board board,Position currentPosition){
        return board.getPieces().containsKey(position) && board.getPieces().get(position).getColor()!=board.getPieces().get(currentPosition).getColor();
    }
    private boolean isNotOutOfBounds(Position position, Board board){
        return position.x()>=0 && position.x()<board.getSizeOfRows() && position.y()>=0 && position.y()<board.getSizeOfRows();
    }

}
