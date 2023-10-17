package game.interfaces;


import enums.Color;
import game.Player;
import piece.Movement;
import result.MoveResult;
import result.Result;
public interface GameOrganizer {

    Game currentGame();
//TODO implement this promotions and other stuff
//    List<GameMover> getGameMovers();
//
//    TurnHandler getTurnHandler();
//
//    Result<Boolean, Color> isGameOver(Movement movement);
//
//    Result<GameOrganizer,String> tryMovement(Movement movement, Game game);

    GameMover getGameMover();
    TurnHandler getTurnHandler();
    Player getWinner();

    MoveResult<GameOrganizer,String> tryMovement(Movement movement, Game game);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();

    GameOverCondition getGameOverCondition();
}
