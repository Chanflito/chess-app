package common.game.interfaces;

import common.enums.Color;

public interface TurnHandler {

    Color getCurrentTurn();
    TurnHandler nextTurn();


}