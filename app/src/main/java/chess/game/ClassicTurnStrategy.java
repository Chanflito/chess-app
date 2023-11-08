package chess.game;

import common.enums.Color;
import common.game.interfaces.GameState;
import common.game.interfaces.TurnStrategy;

public class ClassicTurnStrategy implements TurnStrategy {


    @Override
    public Color nextTurn(GameState gameState) {
        return gameState.currentTurn()==Color.WHITE? Color.BLACK: Color.WHITE;
    }

}
