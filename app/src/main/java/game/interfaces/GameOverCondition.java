package game.interfaces;


import enums.Color;
import piece.Movement;
import result.Result;

public interface GameOverCondition {
    Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer);
}
