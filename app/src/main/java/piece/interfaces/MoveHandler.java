package piece.interfaces;

import board.interfaces.Board;
import piece.Movement;


public interface MoveHandler {

    boolean checkOrValidators(Movement movement, Board board);

    boolean checkAndValidators(Movement movement, Board board);
    //TODO: Agregar aca un checkWinValidator, y que arroje un
    // objeto tipo T (como el result que nos dijeron en clase)para ver quien gano y demas.
}
