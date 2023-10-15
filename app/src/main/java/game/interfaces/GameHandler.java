package game.interfaces;


import exception.InvalidMovementException;
import exception.InvalidTurnException;
import game.Player;
import piece.Movement;

public interface GameHandler {

    Game currentGame();

    GameMover getGameMover();

    TurnHandler getTurnHandler();

    Player getWinner();

    GameHandler tryMovement(Movement movement, Game game) throws InvalidMovementException, InvalidTurnException;
    GameHandler copy();
}
