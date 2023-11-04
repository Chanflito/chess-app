package checkers.validator;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Piece;
import common.game.Position;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

import java.util.Map;

public class PreviousCaptureValidator implements MovementValidator {

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (board.getPieces().containsKey(movement.getFrom())) {
            Color enemyColor = board.getPieces().get(movement.getFrom()).getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
            PieceCountValidator pieceCountValidator = new PieceCountValidator();
            if(pieceCountValidator.wasCapture(board,enemyColor)) return currentPieceCapture(movement,board);
        }
        return false;
    }



    private boolean currentPieceCapture(Movement movement,Board board){
        Board boardClone=board.copy();
        Piece piece=boardClone.getPieces().get(movement.getFrom());
        Map<Position,Piece> previousBoard=boardClone.getHistory().get(boardClone.getHistory().size()-2);
        Position previousPosition=previousBoard.entrySet().stream().filter(e->e.getValue().equals(piece)).findFirst().get().getKey();
        return Math.abs(previousPosition.x()-movement.getFrom().x())==2 && Math.abs(previousPosition.y()-movement.getFrom().y())==2;
    }
}
