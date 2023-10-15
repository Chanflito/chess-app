package piece.interfaces;

import board.interfaces.Board;
import piece.Movement;
import validator.interfaces.MovementValidator;

import java.util.List;


public interface MoveHandler {

    List<MovementValidator> getOrValidators();

    List<MovementValidator> getAndValidators();

    boolean checkOrValidators(Movement movement, Board board);

    boolean checkAndValidators(Movement movement, Board board);
    //TODO: Agregar aca un checkWinValidator, y que arroje un
    // objeto tipo T (como el result que nos dijeron en clase)para ver quien gano y demas.
}
