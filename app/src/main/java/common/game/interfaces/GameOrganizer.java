package common.game.interfaces;


import common.enums.Color;
import common.move.Movement;
import common.result.Result;
public interface GameOrganizer {
    GameData currentGame();
    
    GameMover getGameMover();
    TurnHandler getTurnHandler();

    Result<?,?> move(Movement movement, GameData gameData);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();
}
