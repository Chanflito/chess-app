package piece.interfaces;

import validator.interfaces.MovementValidator;

import java.util.List;


public interface MoveHandler {
    List<MovementValidator> getOrValidators();

    List<MovementValidator> getAndValidators();

    boolean checkOrValidators();

    boolean checkAndValidators();

}
