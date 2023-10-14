package piece.interfaces;

import board.interfaces.Board;
import exception.InvalidMovementException;
import piece.Movement;
import validator.interfaces.MovementValidator;

import java.util.List;


public interface MoveHandler {
    List<MovementValidator> getOrValidators();

    List<MovementValidator> getAndValidators();

    boolean checkOrValidators(Movement movement, Board board) throws InvalidMovementException;

    boolean checkAndValidators(Movement movement, Board board) throws InvalidMovementException;
    //TODO: Agregar aca un checkWinValidator, y que arroje un
    // objeto tipo T (como el result que nos dijeron en clase)para ver quien gano y demas.
}
