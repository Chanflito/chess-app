package game.interfaces;

import enums.Color;
import piece.Movement;
import result.Result;

public interface GameMover {
    Result<Game, Color> movePiece(Movement movement, Game game);
}
