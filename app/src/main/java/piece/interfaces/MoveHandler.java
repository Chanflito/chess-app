package piece.interfaces;

import board.interfaces.Board;
import piece.Movement;



public interface MoveHandler {


    boolean checkOrValidators(Movement movement, Board board);

    boolean checkAndValidators(Movement movement, Board board);

}
