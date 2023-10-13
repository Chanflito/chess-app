package game;

import enums.Color;
import game.interfaces.TurnHandler;

public class ClassicTurnHandler implements TurnHandler {
    //NO ES PUROOOOOO
    private final Color currentTurn;

    public ClassicTurnHandler(Color turn) {
        this.currentTurn = turn;
    }

    @Override
    public Color getCurrentTurn() {
        return this.currentTurn;
    }

    @Override
    public TurnHandler nextTurn() {
        return this.currentTurn==Color.WHITE? new ClassicTurnHandler(Color.BLACK): new ClassicTurnHandler(Color.WHITE);
    }

}
