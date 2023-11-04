package checkers.game;

import checkers.helper.TurnHelper;
import common.enums.Color;
import common.game.interfaces.TurnHandler;

import java.util.Optional;

public class CheckersTurnHandler implements TurnHandler {
    private final Color currentTurn;

    private final TurnHelper turnHelper;

    public CheckersTurnHandler(Color currentTurn, TurnHelper turnHelper) {
        this.currentTurn = currentTurn;
        this.turnHelper = turnHelper;
    }

    public CheckersTurnHandler(Color currentTurn){
        this.currentTurn=currentTurn;
        this.turnHelper=null;
    }
    @Override
    public Color getCurrentTurn() {
        return this.currentTurn;
    }

    @Override
    public TurnHandler nextTurn() {
        if (getTurnHelper().isPresent()){
            boolean notChangeTurn= getTurnHelper().get().notChangeTurn();
            if (notChangeTurn){
                return new CheckersTurnHandler(this.currentTurn);
            }
        }
        return new CheckersTurnHandler(this.currentTurn==Color.WHITE ? Color.BLACK : Color.WHITE);
    }

    private Optional<TurnHelper> getTurnHelper() {
        return Optional.ofNullable(this.turnHelper);
    }
}
