package game;

import enums.Color;
import game.interfaces.GameOrganizer;
import game.interfaces.GameOverCondition;
import piece.Movement;
import result.Result;


public class ThreeCheckGameOverCondition implements GameOverCondition {
    // TODO: TO BE IMPLEMENTED.

    @Override
    public Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        return null;
    }
}
