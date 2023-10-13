package game.interfaces;


public interface GameHandler {

    Game currentGame();

    GameMover getGameMover();

    TurnHandler getTurnHandler();

    GameHandler copy();
}
