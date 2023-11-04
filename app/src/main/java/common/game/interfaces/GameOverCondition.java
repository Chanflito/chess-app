package common.game.interfaces;


import common.enums.Color;
import common.move.Movement;
import common.result.Result;

public interface GameOverCondition {
    Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer);
}
