package validator;

import board.Position;
import board.interfaces.Board;
import enums.Color;
import enums.PieceType;
import piece.Movement;
import piece.Piece;
import validator.interfaces.MovementValidator;

import java.util.Map;

public class CheckValidator implements MovementValidator {
    //Un jugador no puede hacer ningún movimiento que coloque o deja a su rey en jaque...
    //TODO: Agregar a los validaroes un MoveResult<Boolean,String>
    // para que devuelvan un mensaje de error correspondiente.

    public CheckValidator() {
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        Color kingColorToFind=board.getPieces().get(movement.getFrom()).getColor();
        Map<Position, Piece> pieces=board.getPieces();
        return checkIsValid(board, kingColorToFind, pieces);
    }


    private boolean checkIsValid(Board board, Color kingColorToFind, Map<Position, Piece> pieces) {
        for(Map.Entry<Position,Piece> playerMap: pieces.entrySet()){
            if (playerMap.getValue().getColor().equals(kingColorToFind)){
                if (playerMap.getValue().getType().equals(PieceType.KING)){
                    Color otherColor= playerMap.getValue().getColor().equals(Color.WHITE)? Color.BLACK: Color.WHITE;
                    return checkMovementsOfEnemy(board,pieces,playerMap,otherColor);
                }
            }
        }
        return false;
    }

    private boolean checkMovementsOfEnemy(Board board, Map<Position, Piece> pieces, Map.Entry<Position, Piece> playerMap, Color enemyColor) {
        for (Map.Entry<Position,Piece> enemyMap: pieces.entrySet()){
            if (enemyMap.getValue().getColor().equals(enemyColor)){
                if (checkValidatorsOfEnemy(board, playerMap, enemyMap)) return true;
            }
        }
        return false;
    }

    private  boolean checkValidatorsOfEnemy(Board board, Map.Entry<Position, Piece> p, Map.Entry<Position, Piece> e) {
        return e.getValue().getMoveHandler().checkOrValidators
                (new Movement(e.getKey(), p.getKey()), board) &&
                !e.getValue().getMoveHandler().checkAndValidators(new Movement(e.getKey(), p.getKey()), board);
    }
}
