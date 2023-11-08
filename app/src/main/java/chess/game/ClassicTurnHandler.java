package chess.game;

import common.enums.Color;
import common.game.interfaces.GameData;
import common.game.interfaces.TurnHandler;

public class ClassicTurnHandler implements TurnHandler {


    @Override
    public Color nextTurn(GameData gameData) {
        return gameData.currentTurn()==Color.WHITE? Color.BLACK: Color.WHITE;
    }

}
