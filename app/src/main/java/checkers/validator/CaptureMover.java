package checkers.validator;

import chess.board.Position;
import chess.piece.Movement;
import chess.piece.Piece;
import chess.result.Result;
import common.board.interfaces.Board;
import common.enums.Color;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;

import java.util.List;
import java.util.Map;

public class CaptureMover implements PieceMover {

    private final List<MovementValidator> validators;

    public CaptureMover(List<MovementValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        for (MovementValidator v: validators) {
            if (!v.isValid(movement, board)) return false;
        }
        return true;
    }

    @Override
    public Result<Board, Boolean> move(Movement movement, Board board) {
        Position from= movement.getFrom().copy();
        Position to=movement.getTo().copy();//TODO
        Board boardClone=board.copy();
        int distanceX=calculateDistance(from.x(),to.x());
        int distanceY=calculateDistance(from.y(),to.y());
        Color enemyColor= getColor(from, boardClone) ==Color.WHITE ? Color.BLACK : Color.WHITE;
        if (isDiagonalMovement(distanceX,distanceY)){
            Position middlePosition= new Position((from.x()+to.x())/2,(from.y()+to.y())/2);
            if (isEnemyPiece(middlePosition,boardClone,enemyColor)){
                return null;
            }
        }
        return null;
    }

    private Color getColor(Position from, Board boardClone) {
        return boardClone.getPieces().get(from).getColor();
    }

    private int calculateDistance(int d1,int d2){
        return Math.abs(d1-d2);
    }

    private boolean isEnemyPiece(Position position, Board board,Color enemyColor){
        if (!board.getPieces().containsKey(position)) return false;
        return getColor(position,board)==enemyColor;
    }

    private boolean isDiagonalMovement(int distanceX,int distanceY){
        return distanceX==distanceY;
    }

    private boolean checkPreviousCapture(Board board, Color enemyPieceColor){
        List<Map<Position, Piece>> previousBoards=board.getHistory();
        if (previousBoards.size()<2) return false;
        Map<Position,Piece> previousBoard=previousBoards.get(previousBoards.size()-2);
        Map<Position,Piece> currentBoard=previousBoards.get(previousBoards.size()-1);
        int previousEnemyPieces=0;
        int currentEnemyPieces=0;
        for (Map.Entry<Position,Piece> entry: previousBoard.entrySet()){
            if (entry.getValue().getColor()==enemyPieceColor) previousEnemyPieces++;
        }
        for (Map.Entry<Position,Piece> entry: currentBoard.entrySet()){
            if (entry.getValue().getColor()==enemyPieceColor) currentEnemyPieces++;
        }
        return previousEnemyPieces!=currentEnemyPieces;
    }

    
}
