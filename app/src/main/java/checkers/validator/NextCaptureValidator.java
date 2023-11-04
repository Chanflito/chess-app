package checkers.validator;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Position;
import common.move.Movement;
import common.validator.interfaces.MovementValidator;

import java.util.List;
import java.util.Optional;

public class NextCaptureValidator implements MovementValidator {
    @Override
    public boolean isValid(Movement movement, Board board) {
        Optional<Position> position = evaluatePossibleCaptureMovement(movement, board);
        return position.isPresent();
    }

    private Optional<Position> evaluatePossibleCaptureMovement(Movement movement, Board board) {
        Board boardClone= board.copy();
        Position currentPosition= movement.getFrom().copy();
        Position nextPosition= movement.getTo().copy();
        boardClone.getPieces().put(nextPosition,boardClone.getPieces().get(currentPosition));
        boardClone.getPieces().remove(currentPosition);
        Color enemyColor= boardClone.getPieces().get(nextPosition).getColor()==Color.WHITE ? Color.BLACK : Color.WHITE;
        List<Position> posibleCaptureDirections=List.of(new Position(2,2),new Position(2,-2),new Position(-2,2),new Position(-2,-2));
        return nextPositionIsEqualOfPossibleCaptureDirections(nextPosition,posibleCaptureDirections, board,enemyColor);
    }


    private Optional<Position> nextPositionIsEqualOfPossibleCaptureDirections(Position nextPosition,
                                                                             List<Position> posibleCaptureDirections,
                                                                             Board board,
                                                                             Color enemyColor){
        for (Position p:posibleCaptureDirections){
            Position evaluate=new Position(nextPosition.x()+p.x(),nextPosition.y()+p.y());
            if (!board.getPieces().containsKey(evaluate)){
                Position middlePosition= new Position((evaluate.x())/2,(evaluate.y())/2);
                if (isEnemyPiece(middlePosition,board,enemyColor)) return Optional.of(evaluate);
            }
        }
        return Optional.empty();
    }

    private boolean isEnemyPiece(Position position, Board board, Color enemyColor){
        return board.getPieces().containsKey(position) && board.getPieces().get(position).getColor()==enemyColor;
    }
}