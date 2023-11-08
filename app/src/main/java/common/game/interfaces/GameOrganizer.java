package common.game.interfaces;


import common.enums.Color;
import common.move.Movement;
import common.result.Result;
public interface GameOrganizer {
    GameState currentGame();
    
    GameMover getGameMover();
    TurnStrategy getTurnHandler();

    Result<?,?> move(Movement movement, GameState gameState);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();
}
