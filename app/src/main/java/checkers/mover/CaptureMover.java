package checkers.mover;

import common.game.Position;
import common.move.Movement;
import common.game.Piece;
import common.result.MoveResult;
import common.result.Result;
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
        if (!isValid(movement, board)) return new MoveResult<>(board, false);
        Position from= movement.getFrom().copy();
        Position to=movement.getTo().copy();
        Board boardClone=board.copy();
        int distanceX=calculateDistance(from.x(),to.x());
        int distanceY=calculateDistance(from.y(),to.y());
        Color enemyColor= getColor(from, boardClone) ==Color.WHITE ? Color.BLACK : Color.WHITE;
        if (isDiagonalMovement(distanceX,distanceY)){
            Position middlePosition= new Position((from.x()+to.x())/2,(from.y()+to.y())/2);
            if (isEnemyPiece(middlePosition,boardClone,enemyColor)){
                boardClone.getPieces().remove(middlePosition);
                boardClone.getPieces().put(to,boardClone.getPieces().get(from));
                boardClone.getPieces().remove(from);
                return new MoveResult<>(boardClone, true);
            }
        }
        return new MoveResult<>(boardClone, false);
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


}
