package game.interfaces;


import exception.InvalidMovementException;
import exception.InvalidTurnException;
import piece.Movement;

public interface GameHandler {

    Game currentGame();

    GameMover getGameMover();

    TurnHandler getTurnHandler();

    GameHandler tryMovement(Movement movement, Game game) throws InvalidMovementException, InvalidTurnException;
    GameHandler copy();
}
