package chess.validator;

import common.game.Position;
import common.board.interfaces.Board;
import common.enums.Direction;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

public class PathValidator implements MovementValidator {
    private final int columns;

    private final int rows;

    public PathValidator(Direction direction) {
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
            if (evaluate.equals(finalPosition)) return true;
            if (board.getPieces().containsKey(evaluate)) return false;
        }
        return true;
    }
}
