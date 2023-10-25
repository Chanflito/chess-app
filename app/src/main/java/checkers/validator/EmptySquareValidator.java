package checkers.validator;

import chess.piece.Movement;
import common.board.interfaces.Board;
import common.validator.interfaces.MovementValidator;

public class EmptySquareValidator implements MovementValidator {
    @Override
    public boolean isValid(Movement movement, Board board) {
        return !board.getPieces().containsKey(movement.getTo());
    }
}
