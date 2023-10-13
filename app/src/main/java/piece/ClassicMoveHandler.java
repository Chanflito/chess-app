package piece;

import board.interfaces.Board;
import piece.interfaces.MoveHandler;
import validator.interfaces.MovementValidator;

import java.util.List;

public class ClassicMoveHandler implements MoveHandler {
    private final List<MovementValidator> orValidator;
    private final  List<MovementValidator> andValidator;

    public ClassicMoveHandler(List<MovementValidator> orValidator, List<MovementValidator> andValidator) {
        this.orValidator = orValidator;
        this.andValidator = andValidator;
    }

    @Override
    public List<MovementValidator> getOrValidators() {
        return this.orValidator;
    }

    @Override
    public List<MovementValidator> getAndValidators() {
        return this.andValidator;
    }

    @Override
    public boolean checkOrValidators(Movement movement, Board board) {
        for (MovementValidator m: orValidator) {
            if (m.isValid(movement,board)) return true;
        }
        return false;
    }

    @Override
    public boolean checkAndValidators(Movement movement, Board board) {
        for (MovementValidator m: andValidator) {
            if (!m.isValid(movement,board)) return false;
        }
        return true;
    }
}
