package common.game.interfaces;


import common.enums.Color;
import chess.piece.Movement;
import chess.result.MoveResult;
import chess.result.Result;
public interface GameOrganizer {

    Game currentGame();
    
    GameMover getGameMover();
    TurnHandler getTurnHandler();

    MoveResult<GameOrganizer,String> tryMovement(Movement movement, Game game);

    Result<Boolean,Color> isGameOver(Movement movement);
    GameOrganizer copy();

    GameOverCondition getGameOverCondition();
}
