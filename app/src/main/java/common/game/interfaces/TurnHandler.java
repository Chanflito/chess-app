package common.game.interfaces;


import common.enums.Color;

public interface TurnHandler {

    Color nextTurn(GameData gameData);

}