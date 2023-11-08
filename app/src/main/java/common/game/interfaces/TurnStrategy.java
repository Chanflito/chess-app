package common.game.interfaces;


import common.enums.Color;

public interface TurnStrategy {

    Color nextTurn(GameState gameState);

}