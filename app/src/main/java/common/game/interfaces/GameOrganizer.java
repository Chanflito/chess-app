package common.game.interfaces;


import common.enums.Color;
import common.move.Movement;
import common.result.MoveResult;
import common.result.Result;
public interface GameOrganizer {

    Game currentGame();
    
    GameMover getGameMover();
    TurnHandler getTurnHandler();

    MoveResult<GameOrganizer,String> move(Movement movement, Game game);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();
}
