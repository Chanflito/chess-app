package checkers.validator;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Position;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

import java.util.List;

public class CanCaptureValidator implements MovementValidator {
    private final List<Position> directionsToCapture;

    public CanCaptureValidator(List<Position> directionsToCapture) {
        this.directionsToCapture = directionsToCapture;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        return directionsToCapture.stream().anyMatch(p->isValidDirection(movement,p,board));
    }

    private boolean isValidDirection(Movement movement, Position direction, Board board){
        Position from= movement.getFrom().copy();
        Color color= board.getPieces().get(from).getColor();
        Position evaluate=new Position(calculateX(direction, from), calculateY(direction, from));
        if (!board.getPieces().containsKey(evaluate) && isNotOutOfBounds(evaluate,board)){
            Position middlePosition= new Position((evaluate.x()+ from.x())/2,(evaluate.y()+ from.y())/2);
            return isEnemyPiece(middlePosition,board,color);
        }
        return false;
    }

    private int calculateY(Position direction, Position from) {
        return Math.abs(from.y() + direction.y());
    }

    private int calculateX(Position direction, Position from) {
        return Math.abs(from.x() + direction.x());
    }

    private boolean isEnemyPiece(Position position, Board board,Color color){
        return board.getPieces().containsKey(position) && board.getPieces().get(position).getColor()!=color;
    }

    private boolean isNotOutOfBounds(Position position, Board board){
        return position.x()>=0 && position.x()<board.getSizeOfRows() && position.y()>=0 && position.y()<board.getSizeOfRows();
    }
}
