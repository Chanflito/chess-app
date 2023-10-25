package chess.game;

import common.enums.Color;
import common.game.interfaces.GameOrganizer;
import common.game.interfaces.GameOverCondition;
import chess.piece.Movement;
import chess.result.Result;


public class ThreeCheckGameOverCondition implements GameOverCondition {
    // TODO: TO BE IMPLEMENTED.

    @Override
    public Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        return null;
    }
}
