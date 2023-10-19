package piece.interfaces;

import board.interfaces.Board;
import piece.Movement;
import result.Result;


public interface MoveHandler {


    boolean checkOrValidators(Movement movement, Board board);
    //And validator podria ir afuer, no en la pieza.

    boolean checkAndValidators(Movement movement, Board board);

    Result<Board,Boolean> checkSpecialValidators(Movement movement,Board board);
}
