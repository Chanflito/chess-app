package chess.validator;

import common.game.Position;
import common.board.interfaces.Board;
import common.move.Movement;
import common.game.Piece;
import common.validator.interfaces.MovementValidator;

import java.util.List;
import java.util.Map;

public class QuantityMovementValidator implements MovementValidator {
    private final int movements;

    public QuantityMovementValidator(int movements) {
        this.movements = movements;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        Position currentPosition=movement.getFrom().copy();
        Piece piece=board.getPieces().get(currentPosition);
        List<Map<Position,Piece>> historyOfMovements= board.getHistory();
        int counter=0;
        return validateDistinctMovements(currentPosition, piece, historyOfMovements, counter);
    }

    private boolean validateDistinctMovements(Position currentPosition, Piece piece, List<Map<Position, Piece>> historyOfMovements, int counter) {
        for (int i = historyOfMovements.size()-1; i>=0; i--){
            Map<Position,Piece> current= historyOfMovements.get(i);
            if (!current.containsKey(currentPosition)){
                //I have to search the piece if not equals.
                for (Map.Entry<Position,Piece> entry:current.entrySet()){
                    if (entry.getValue().equals(piece)){
                        currentPosition =entry.getKey();
                        counter++;
                    }
                }
            }
        }
        return counter <= movements;
    }
}
