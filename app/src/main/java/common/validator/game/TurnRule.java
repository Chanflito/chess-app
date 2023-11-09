package common.validator.game;

import common.enums.Color;
import common.game.interfaces.GameState;
import common.move.Movement;
import common.result.Result;
import common.result.ValidatorResult;
import common.validator.interfaces.GameRule;

import java.util.Optional;

public class TurnRule implements GameRule {
    @Override
    public Result<Boolean, String> isValid(Movement move, GameState gameState) {
        return isYourTurn(move, gameState) ?
                new ValidatorResult<>(true, Optional.empty()) :
                new ValidatorResult<>(false, Optional.of("It's not your turn"));
    }

    private boolean isYourTurn(Movement move, GameState gameState){
        return gameState.currentTurn().equals(getPieceColor(move, gameState));
    }

    private Color getPieceColor(Movement move, GameState gameState){
        return gameState.getBoard().getPieces().get(move.getFrom()).getColor();
    }
}
