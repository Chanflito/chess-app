package game.interfaces;

import piece.Movement;

public interface GameMover {
    Game movePiece(Movement movement, Game game);
}
