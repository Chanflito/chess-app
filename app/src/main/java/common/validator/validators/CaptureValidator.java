package common.validator.validators;

import common.board.interfaces.Board;
import common.move.Movement;
import common.game.Piece;
import common.validator.interfaces.MovementValidator;

public class CaptureValidator implements MovementValidator {

    private final boolean isCaptureEnabled;

    public CaptureValidator(boolean isCaptureEnabled) {
        this.isCaptureEnabled = isCaptureEnabled;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (!isCaptureEnabled){
            return !board.getPieces().containsKey(movement.getTo());
        }
        if (board.getPieces().containsKey(movement.getTo())){
            Piece pieceToCapture=board.getPieces().get(movement.getTo());
            return pieceToCapture.getColor() != board.getPieces().get(movement.getFrom()).getColor();
        }
        return true;
    }

}
