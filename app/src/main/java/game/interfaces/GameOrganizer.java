package game.interfaces;


import enums.Color;
import piece.Movement;
import result.MoveResult;
import result.Result;
public interface GameOrganizer {

    Game currentGame();


    GameMover getGameMover();
    TurnHandler getTurnHandler();

    MoveResult<GameOrganizer,String> tryMovement(Movement movement, Game game);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();

    GameOverCondition getGameOverCondition();
}
