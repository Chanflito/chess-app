package checkers.game;

import checkers.helper.TurnHelper;
import common.enums.Color;
import common.game.interfaces.GameState;
import common.game.interfaces.TurnStrategy;

import java.util.Optional;

public class CheckersTurnStrategy implements TurnStrategy {

    private final TurnHelper turnHelper;


    public CheckersTurnStrategy(TurnHelper turnHelper) {
        this.turnHelper = turnHelper;
    }


    @Override
    public Color nextTurn(GameState gameState) {
        if (getTurnHelper().isPresent()){
            boolean notChangeTurn= getTurnHelper().get().notChangeTurn(gameState.getBoard());
            if (notChangeTurn){
                return gameState.currentTurn();
            }
        }
        return  gameState.currentTurn()==Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private Optional<TurnHelper> getTurnHelper() {
        return Optional.ofNullable(this.turnHelper);
    }
}
