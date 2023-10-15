package piece;

import board.interfaces.Board;
import piece.interfaces.MoveHandler;
import validator.interfaces.MovementValidator;

import java.util.List;

public class ClassicMoveHandler implements MoveHandler {
    private final List<MovementValidator> orValidators;
    private final  List<MovementValidator> andValidators;

    public ClassicMoveHandler(List<MovementValidator> orValidators, List<MovementValidator> andValidators) {
        this.orValidators = orValidators;
        this.andValidators = andValidators;
    }

    @Override
    public List<MovementValidator> getOrValidators() {
        return this.orValidators;
    }

    @Override
    public List<MovementValidator> getAndValidators() {
        return this.andValidators;
    }

    @Override
    public boolean checkOrValidators(Movement movement, Board board) {
        for (MovementValidator m: orValidators) {
            if (m.isValid(movement,board)) return true;
        }
        return false;
    }
    //TODO: Agregar un turn validator en el andValidator
    @Override
    public boolean checkAndValidators(Movement movement, Board board) {
        for (MovementValidator m: andValidators) {
            if (m.isValid(movement,board)) return false;
        }
        return true;
    }
    //Todo agregar un validador del jaque mate especial.

}
