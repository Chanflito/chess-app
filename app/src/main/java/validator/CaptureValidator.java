package validator;

import board.interfaces.Board;
import piece.Movement;
import piece.Piece;
import validator.interfaces.MovementValidator;

public class CaptureValidator implements MovementValidator {

    private final boolean isCaptureEnabled;

    public CaptureValidator(boolean isCaptureEnabled) {
        this.isCaptureEnabled = isCaptureEnabled;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (!isCaptureEnabled) return false;
        if (board.getPieces().containsKey(movement.getTo())){
            Piece pieceToCapture=board.getPieces().get(movement.getTo()).copy();
            return pieceToCapture.getColor() != board.getPieces().get(movement.getFrom()).getColor();
        }
        return false;
    }
}