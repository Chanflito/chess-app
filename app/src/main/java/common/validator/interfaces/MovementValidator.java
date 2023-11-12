package common.validator.interfaces;

import common.board.interfaces.Board;
import common.move.Movement;

public interface MovementValidator {
    boolean isValid(Movement movement, Board board);


}
