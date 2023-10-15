package game.interfaces;


import game.Player;
import piece.Movement;
import result.Result;

public interface GameHandler {

    Game currentGame();

    GameMover getGameMover();

    TurnHandler getTurnHandler();

    Player getWinner();

    Result<GameHandler,String> tryMovement(Movement movement, Game game);
    GameHandler copy();
}
