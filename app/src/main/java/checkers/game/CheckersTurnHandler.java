package checkers.game;

import checkers.helper.TurnHelper;
import common.enums.Color;
import common.game.interfaces.GameData;
import common.game.interfaces.TurnHandler;

import java.util.Optional;

public class CheckersTurnHandler implements TurnHandler {

    private final TurnHelper turnHelper;


    public CheckersTurnHandler(TurnHelper turnHelper) {
        this.turnHelper = turnHelper;
    }


    @Override
    public Color nextTurn(GameData gameData) {
        if (getTurnHelper().isPresent()){
            boolean notChangeTurn= getTurnHelper().get().notChangeTurn(gameData.getBoard());
            if (notChangeTurn){
                return gameData.currentTurn();
            }
        }
        return  gameData.currentTurn()==Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private Optional<TurnHelper> getTurnHelper() {
        return Optional.ofNullable(this.turnHelper);
    }
}
