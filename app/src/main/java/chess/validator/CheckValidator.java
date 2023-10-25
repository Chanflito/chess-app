package chess.validator;

import chess.board.Position;
import common.board.interfaces.Board;
import common.enums.Color;
import common.enums.PieceType;
import chess.piece.Movement;
import chess.piece.Piece;
import common.validator.interfaces.MovementValidator;

import java.util.Map;

public class CheckValidator implements MovementValidator {
    //Un jugador no puede hacer ningún movimiento que coloque o deja a su rey en jaque...
    //TODO: Agregar a los validaroes un MoveResult<Boolean,String>
    // para que devuelvan un mensaje de error correspondiente.

    public CheckValidator() {
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        Movement movementClone = new Movement(movement.getFrom(), movement.getTo());
        Color kingColorToFind=board.getPieces().get(movement.getFrom()).getColor();
        Map<Position, Piece> pieces=board.getPieces();
        Piece playerPiece=board.getPieces().get(movement.getFrom());
        return checkIsValid(board, kingColorToFind, pieces,movementClone,playerPiece);
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
    private boolean checkValidatorsOfEnemy(Board board, Position kingPosition,
                                            Position enemyPosition ,Piece enemyPiece,Movement movement,Piece piece) {
        Board boardClone=board.copy();
        boardClone.getPieces().put(movement.getTo(),piece);
        boardClone.getPieces().remove(movement.getFrom(),piece);
        Position position= movement.getFrom().equals(kingPosition) ? movement.getTo().copy() : kingPosition.copy();
        if (checkIfEnemyCanCapture(board,enemyPosition,enemyPiece,position)){
            if (checkIfEnemyCanCapture(boardClone,
                    enemyPosition,enemyPiece,position)) return true;
        }
        if (checkIfEnemyCanCapture(boardClone,
                enemyPosition,enemyPiece,position)) return true;
        if (checkIfEnemyCanCapture(board,enemyPosition,enemyPiece,position)){
            if (checkIfEnemyCanCapture(boardClone,
                    enemyPosition,enemyPiece,position)) {
                return checkMovementToEnemy(enemyPosition, movement);
            }
        }
        return false;

    }

    private boolean checkMovementToEnemy(Position enemyPosition, Movement movement) {
        return !movement.getTo().equals(enemyPosition);
    }


    private boolean checkIfEnemyCanCapture(Board board, Position enemyPosition, Piece enemyPiece, Position position) {
        return enemyPiece.getMoveHandler().checkOrValidators(new Movement(enemyPosition, position), board);
    }
}
