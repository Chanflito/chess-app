package common.validator.validators;

import common.board.interfaces.Board;
import common.move.Movement;
import common.game.Piece;
import common.validator.interfaces.MovementValidator;

public class PawnCaptureValidator implements MovementValidator {
    private final boolean isCaptureEnabled;

    public PawnCaptureValidator(boolean isCaptureEnabled) {
        this.isCaptureEnabled = isCaptureEnabled;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        boolean havePiece=board.getPieces().containsKey(movement.getTo());
        if (!isCaptureEnabled){
            return !havePiece;
        }
        if (havePiece){
            Piece pieceToCapture=board.getPieces().get(movement.getTo());
            return pieceToCapture.getColor() != board.getPieces().get(movement.getFrom()).getColor();
        }
        return false;
    }
}