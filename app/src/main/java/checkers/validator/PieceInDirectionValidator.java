package checkers.validator;

import chess.board.Position;
import chess.piece.Movement;
import common.board.interfaces.Board;
import common.enums.Direction;
import common.validator.interfaces.MovementValidator;

public class PieceInDirectionValidator implements MovementValidator {
    private final int columns;

    private final int rows;

    public PieceInDirectionValidator(Direction direction) {
        this.rows= direction.x();
        this.columns= direction.y();
    }


    @Override
    public boolean isValid(Movement movement, Board board) {
        Position initialPosition=movement.getFrom().copy();
        Position finalPosition=movement.getTo().copy();
        int x= initialPosition.x();
        int y= initialPosition.y();
        while (x<=board.getSizeOfRows() -1 && y<= board.getSizeOfColumns() -1){
            x+=rows;
            y+=columns;
            Position evaluate=new Position(x,y);
            if (evaluate.equals(finalPosition)) return false;
            if (board.getPieces().containsKey(evaluate)) return true;
        }
        return false;
    }
}
