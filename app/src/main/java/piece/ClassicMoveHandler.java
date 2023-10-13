package piece;

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
    public boolean checkOrValidators() {
        return false;
    }

    @Override
    public boolean checkAndValidators() {
        return false;
    }
}
