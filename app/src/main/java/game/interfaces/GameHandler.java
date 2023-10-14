package game.interfaces;


import piece.Movement;

public interface GameHandler {

    Game currentGame();

    GameMover getGameMover();

    TurnHandler getTurnHandler();

    GameHandler tryMovement(Movement movement, Game game);
    GameHandler copy();
}
