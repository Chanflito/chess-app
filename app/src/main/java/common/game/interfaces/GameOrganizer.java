package common.game.interfaces;


import common.enums.Color;
import common.move.Movement;
import common.result.MoveResult;
import common.result.Result;
public interface GameOrganizer {

    Game currentGame();
    
    GameMover getGameMover();
    TurnHandler getTurnHandler();

    Result<?,?> move(Movement movement, Game game);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();
}
