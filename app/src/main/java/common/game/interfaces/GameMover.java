package common.game.interfaces;

import common.move.Movement;
import chess.result.Result;

public interface GameMover {
    Result<Game, String> movePiece(Movement movement, Game game);
}
