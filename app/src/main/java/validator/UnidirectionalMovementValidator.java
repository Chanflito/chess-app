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
        Position position= new Position(movement.getFrom().x()+ rows,movement.getFrom().y()+ columns);
        if (checkIfIsInsideBoard(position, board)) return false;
        while (!position.equals(movement.getTo())){
            position= new Position(position.x()+ rows,position.y()+ columns);
            if (checkIfIsInsideBoard(position, board)) return false;
        }
        return true;
    }


    private boolean checkIfIsInsideBoard(Position position,Board board){
        return position.y() < 0 || position.y() >= board.getSizeOfColumns()
                || position.x() < 0 || position.x() >= board.getSizeOfRows();
    }
}
