package game.interfaces;


import game.Player;
import piece.Movement;
import result.Result;

public interface GameOverCondition {
    Result<Boolean,Player> evaluateCondition(Movement movement, GameHandler gameHandler);
}
