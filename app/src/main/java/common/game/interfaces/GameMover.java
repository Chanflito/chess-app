package common.game.interfaces;

import common.move.Movement;
import common.result.Result;

public interface GameMover {
    Result<GameData, String> movePiece(Movement movement, GameData gameData);
}
