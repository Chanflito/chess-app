package chess.game;

import common.enums.Color;
import common.game.interfaces.GameOrganizer;
import common.game.interfaces.GameOverCondition;
import common.move.Movement;
import common.result.MoveResult;
import common.result.Result;
import common.result.WinResult;

import java.util.List;

public class CompositeOrGameOverCondition implements GameOverCondition {
    //Esta clase deberia ser un composite de condiciones de fin de juego, y devolver true si alguna de las condiciones es true.
    //Se puede implementar el 3 jaques con este.
    private final List<GameOverCondition> gameOverConditions;

    public CompositeOrGameOverCondition(List<GameOverCondition> gameOverConditions) {
        this.gameOverConditions = gameOverConditions;
    }

    @Override
    public Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        for (GameOverCondition g: gameOverConditions) {
            Result<Boolean, Color> result = g.evaluateCondition(movement,gameOrganizer);
            if (result.key()) return new WinResult(result.key(),result.value().get());
        }
        return new MoveResult<>(false, null);
    }
}
