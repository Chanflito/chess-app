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
        Piece playerPiece=board.getPieces().get(movement.getFrom());
        return checkIsValid(board, kingColorToFind, pieces,movement,playerPiece);
    }


    private boolean checkIsValid(Board board, Color kingColorToFind, Map<Position, Piece> pieces,
                                 Movement movement,Piece playerPiece) {
        for(Map.Entry<Position,Piece> playerMap: pieces.entrySet()){
            if (playerMap.getValue().getColor().equals(kingColorToFind)){
                if (playerMap.getValue().getType().equals(PieceType.KING)){
                    Position kingPosition=playerMap.getKey();
                    Color otherColor= playerMap.getValue().getColor().equals(Color.WHITE)? Color.BLACK: Color.WHITE;
                    return checkMovementsOfEnemy(board,pieces,kingPosition,otherColor,movement,playerPiece);
                }
            }
        }
        return false;
    }

    private boolean checkMovementsOfEnemy(Board board, Map<Position, Piece> pieces,
                                          Position kingPosition, Color enemyColor,Movement movement,Piece piece) {
        for (Map.Entry<Position,Piece> enemyMap: pieces.entrySet()){
            if (enemyMap.getValue().getColor().equals(enemyColor)){
                Position enemyPosition=enemyMap.getKey();
                Piece enemyPiece=enemyMap.getValue();
                if (checkValidatorsOfEnemy(board, kingPosition, enemyPosition,enemyPiece,movement,piece)){
                    return true;
                }
            }
        }
        return false;
    }
    //TODO: Make more legible this method
    private  boolean checkValidatorsOfEnemy(Board board, Position kingPosition,
                                            Position enemyPosition ,Piece enemyPiece,Movement movement,Piece piece) {
        Board boardClone=board.copy();
        boardClone.getPieces().put(movement.getTo(),piece);
        return enemyPiece.getMoveHandler().
                checkOrValidators(new Movement(enemyPosition, kingPosition), board) && enemyPiece.getMoveHandler().
                checkOrValidators(new Movement(enemyPosition, kingPosition), boardClone)
                &&!movement.getTo().equals(enemyPosition) ;
    }
}
