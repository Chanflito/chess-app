package chess.game;

import common.game.Position;
import common.board.interfaces.Board;
import common.game.interfaces.Game;
import common.game.interfaces.GameOverCondition;
import common.enums.Color;
import common.game.interfaces.GameOrganizer;
import org.jetbrains.annotations.Nullable;
import common.move.Movement;
import common.game.Piece;
import common.result.Result;
import common.result.WinResult;

import java.util.*;


public class ClassicWinCondition implements GameOverCondition {

    //Si ninguna puede realizar movimientos, es porque estan en jaque , y si ninguna puede zafar del jaque, se termina la partida.

    @Override
    public Result<Boolean,Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        Board currentBoard= gameOrganizer.currentGame().getBoard().copy();
        Color currentColor= gameOrganizer.getTurnHandler().getCurrentTurn();
        Color oponentColor= currentColor== Color.BLACK ? Color.WHITE : Color.BLACK;
        Movement movementOfCurrentPlayer= new Movement(movement.getFrom(),movement.getTo());
        Game game = new ClassicGame(gameOrganizer.currentGame().getPlayers(),currentBoard);
        //Si mi movimiento es valido, tengo que probar si deja en jaque al otro.
        if (checkIfOwnMovementIsValid(gameOrganizer, movementOfCurrentPlayer,game)){
            Piece pieceToMove= currentBoard.getPieces().get(movement.getFrom());
            Position initialPosition= movement.getFrom();
            currentBoard.getPieces().remove(initialPosition);
            currentBoard.getPieces().put(movement.getTo(),pieceToMove.copy());//Hago que el tablero que estoy evaluando tenga la pieza con el movimiento.
            List<Position>positionsOfEnemy=getPositionsOfPiecesOfColor(currentBoard,oponentColor);
            WinResult checkWon = getWinResult(currentBoard, positionsOfEnemy);
            if (checkWon != null) return checkWon;
            return new WinResult(true,currentColor);
        }
        return new WinResult(false,null);
    }

    @Nullable
    private WinResult getWinResult(Board currentBoard, List<Position> positionsOfEnemy) {
        for (Position position: positionsOfEnemy){
            for (int i = 0; i < currentBoard.getSizeOfColumns(); i++) {
                for (int j = 0; j < currentBoard.getSizeOfRows(); j++) {
                    //Pruebo todos los casos posibles de movimiento del oponente.
                    Movement move= new Movement(position,new Position(i,j));
                    Piece piece= currentBoard.getPieces().get(position);
                    if (checkMovementsOfEnemy(currentBoard, move, piece).getValue().get()){
                        //Si alguno de los movimientos del oponente es valido, entonces no hay jaque mate.
                        return new WinResult(false, null);
                    }
                }
            }
        }
        return null;
    }

    private Result<Board, Boolean> checkMovementsOfEnemy(Board currentBoard, Movement move, Piece piece) {
        return piece.getMoveHandler().handleMove(move,currentBoard);
    }

    private boolean checkIfOwnMovementIsValid(GameOrganizer gameOrganizer, Movement movementOfCurrentPlayer,Game game) {
        return gameOrganizer.getGameMover().movePiece(movementOfCurrentPlayer,game).getValue().isEmpty();
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
