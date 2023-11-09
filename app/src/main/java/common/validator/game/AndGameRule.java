package common.validator.game;

import common.game.interfaces.GameState;
import common.move.Movement;
import common.result.Result;
import common.result.ValidatorResult;
import common.validator.interfaces.GameRule;

import java.util.List;
import java.util.Optional;

public class AndGameRule implements GameRule {
    private final List<GameRule> validators;

    public AndGameRule(List<GameRule> validators) {
        this.validators = validators;
    }

    @Override
    public Result<Boolean, String> isValid(Movement move, GameState gameState) {
        for (GameRule validator: validators){
            Result<Boolean,String> result = validator.isValid(move, gameState);
            if (!result.key()){
                return result;
            }
        }
        return new ValidatorResult<>(true, Optional.empty());
    }
}
