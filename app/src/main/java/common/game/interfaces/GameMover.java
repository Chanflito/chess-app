package common.game.interfaces;

import common.move.Movement;
import common.result.Result;

public interface GameMover {
    Result<GameState, String> movePiece(Movement movement, GameState gameState);
}
