package validator;

import board.Position;
import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

public class PathValidator implements MovementValidator {
    private final int columns;

    private final int rows;

    public PathValidator(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        Position initialPosition=movement.getFrom().copy();
        Position finalPosition=movement.getTo().copy();
        int x= initialPosition.x();
        int y= initialPosition.y();
        while (x<=board.getRows() -1 && y<= board.getColumns() -1){
            x+=rows;
            y+=columns;
            Position evaluate=new Position(x,y);
            if (evaluate.equals(finalPosition)) return true;
            if (board.getPieces().containsKey(evaluate)) return false;
        }
        return true;
    }
}
