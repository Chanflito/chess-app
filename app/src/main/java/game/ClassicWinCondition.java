package game;

import board.Position;
import board.interfaces.Board;
import enums.Color;
import game.interfaces.GameHandler;
import game.interfaces.GameOverCondition;
import piece.Movement;
import piece.Piece;
import result.Result;

import java.util.Map;

public class ClassicWinCondition implements GameOverCondition {

    //Debo ver si mis piezas pueden realizar cualquier movimiento hacia cualquier parte del tablero.
    //Si ninguna puede realizar movimientos, es porque estan en jaque , y si ninguna puede zafar del jaque, se termina la partida.

    @Override
    public Result<Boolean, Player> evaluateCondition(Movement movement, GameHandler gameHandler) {
        Movement cloneMovement=new Movement(movement.getFrom(),movement.getTo());
        Board currentBoard=gameHandler.currentGame().getBoard().copy();
        Color oponentColor= gameHandler.getTurnHandler().getCurrentTurn()== Color.BLACK ? Color.WHITE : Color.BLACK;
        Map<Position, Piece> boardPositions=currentBoard.getPieces();
        return null;
    }

}
