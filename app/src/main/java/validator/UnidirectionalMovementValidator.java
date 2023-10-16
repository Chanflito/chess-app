package validator;

import board.Position;
import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

public class UnidirectionalMovementValidator implements MovementValidator {
    private final int columns;

    private final int rows;

    public UnidirectionalMovementValidator(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        Position initialPosition=movement.getFrom().copy();
        Position finalPosition=movement.getTo().copy();
        if (finalPosition.x() >= board.getRows() || finalPosition.y()>= board.getColumns()) return false;
        int x= initialPosition.x();
        int y= initialPosition.y();
        while (Math.abs(x)<=board.getRows() -1 && Math.abs(y)<= board.getColumns() -1){
            x+=rows;
            y+=columns;
            Position evaluate=new Position(x,y);
            if (evaluate.equals(finalPosition)) return true;
        }
        return false;
    }
}
