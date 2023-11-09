package common.validator.interfaces;

import common.game.interfaces.GameState;
import common.move.Movement;
import common.result.Result;

public interface GameRule {

    Result<Boolean,String> isValid(Movement move, GameState gameState);
}
