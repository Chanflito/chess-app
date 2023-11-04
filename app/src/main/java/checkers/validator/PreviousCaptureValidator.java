package checkers.validator;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Piece;
import common.game.Position;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

import java.util.List;
import java.util.Map;

public class PreviousCaptureValidator implements MovementValidator {

    @Override
    public boolean isValid(Movement movement, Board board) {
        if (board.getPieces().containsKey(movement.getFrom())) {
            Color enemyColor = board.getPieces().get(movement.getFrom()).getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
            if(wasCapture(board, enemyColor)) return currentPieceCapture(movement,board);
        }
        return false;
    }

    private boolean wasCapture(Board board, Color enemyPieceColor) {
        List<Map<Position, Piece>> previousBoards = board.getHistory();
        if (previousBoards.size() < 2) return false;
        Map<Position, Piece> previousBoard = previousBoards.get(previousBoards.size() - 2);
        Map<Position, Piece> currentBoard = previousBoards.get(previousBoards.size() - 1);
        int previousEnemyPieces = 0;
        int currentEnemyPieces = 0;
        for (Map.Entry<Position, Piece> entry : previousBoard.entrySet()) {
            if (entry.getValue().getColor() == enemyPieceColor) previousEnemyPieces++;
        }
        for (Map.Entry<Position, Piece> entry : currentBoard.entrySet()) {
            if (entry.getValue().getColor() == enemyPieceColor) currentEnemyPieces++;
        }
        return previousEnemyPieces != currentEnemyPieces;
    }

    private boolean currentPieceCapture(Movement movement,Board board){
        Board boardClone=board.copy();
        Piece piece=boardClone.getPieces().get(movement.getFrom());
        Map<Position,Piece> previousBoard=boardClone.getHistory().get(boardClone.getHistory().size()-2);
        Position previousPosition=previousBoard.entrySet().stream().filter(e->e.getValue().equals(piece)).findFirst().get().getKey();
        return Math.abs(previousPosition.x()-movement.getTo().x())==2 && Math.abs(previousPosition.y()-movement.getTo().y())==2;
    }
}
