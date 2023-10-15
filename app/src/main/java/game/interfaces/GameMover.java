package game.interfaces;

import piece.Movement;
import result.Result;

public interface GameMover {
    Result<Game, String> movePiece(Movement movement, Game game);
}
