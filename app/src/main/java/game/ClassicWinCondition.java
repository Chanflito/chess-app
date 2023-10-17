package game;

import board.Position;
import board.interfaces.Board;
import enums.Color;
import game.interfaces.GameOrganizer;
import game.interfaces.GameOverCondition;
import piece.Movement;
import piece.Piece;
import result.Result;
import result.WinResult;

import java.util.*;


public class ClassicWinCondition implements GameOverCondition {

    //Si ninguna puede realizar movimientos, es porque estan en jaque , y si ninguna puede zafar del jaque, se termina la partida.

    @Override
    public Result<Boolean,Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        Board currentBoard= gameOrganizer.currentGame().getBoard().copy();
        Color currentColor= gameOrganizer.getTurnHandler().getCurrentTurn();
        Color oponentColor= currentColor== Color.BLACK ? Color.WHITE : Color.BLACK;
        Movement movementOfCurrentPlayer= new Movement(movement.getFrom(),movement.getTo());
        //Si mi movimiento es valido, tengo que probar si deja en jaque al otro.
        if (checkIfOwnMovementIsValid(gameOrganizer, movementOfCurrentPlayer)){

            Piece pieceToMove= currentBoard.getPieces().get(movement.getFrom());
            Position initialPosition= movement.getFrom();
            currentBoard.getPieces().remove(initialPosition);
            currentBoard.getPieces().put(movement.getTo(),pieceToMove.copy());//Hago que el tablero que estoy evaluando tenga la pieza con el movimiento.
            List<Position>positionsOfEnemy=getPositionsOfPiecesOfColor(currentBoard,oponentColor);
            for (Position position: positionsOfEnemy){
                for (int i = 0; i < currentBoard.getSizeOfColumns(); i++) {
                    for (int j = 0; j < currentBoard.getSizeOfRows(); j++) {
                        //Pruebo todos los casos posibles de movimiento del oponente.
                        Movement move= new Movement(position,new Position(i,j));
                        Piece piece= currentBoard.getPieces().get(position);
                        if (checkMovementsOfEnemy(currentBoard, move, piece)){
                            //Si alguno de los movimientos del oponente es valido, entonces no hay jaque mate.
                            return new WinResult(false,null);
                        }
                    }
                }
            }
            return new WinResult(true,currentColor);
        }
        return new WinResult(false,null);
    }

    private boolean checkMovementsOfEnemy(Board currentBoard, Movement move, Piece piece) {
        return piece.getMoveHandler().checkAndValidators(move, currentBoard)
                && piece.getMoveHandler().checkOrValidators(move, currentBoard);
    }

    private boolean checkIfOwnMovementIsValid(GameOrganizer gameOrganizer, Movement movementOfCurrentPlayer) {
        return gameOrganizer.getGameMover().movePiece(movementOfCurrentPlayer, gameOrganizer.currentGame()).getValue().isEmpty();
    }


    private List<Position> getPositionsOfPiecesOfColor(Board board, Color color){
        List<Position> positionsOfPiecesOfColor= new ArrayList<>();
        for (Map.Entry<Position, Piece> entry: board.getPieces().entrySet()){
            if (entry.getValue().getColor().equals(color)){
                positionsOfPiecesOfColor.add(entry.getKey());
            }
        }
        return positionsOfPiecesOfColor;
    }
}
