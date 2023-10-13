package game.interfaces;

import enums.Color;

public interface TurnHandler {

    Color getCurrentTurn();
    TurnHandler nextTurn();


}