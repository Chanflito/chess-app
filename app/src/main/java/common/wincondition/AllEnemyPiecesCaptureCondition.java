package common.wincondition;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Piece;
import common.game.interfaces.GameState;
import common.game.interfaces.GameOrganizer;
import common.game.interfaces.GameOverCondition;
import common.move.Movement;
import common.result.Result;
import common.result.WinResult;


public class AllEnemyPiecesCaptureCondition implements GameOverCondition {
    @Override
    public Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        GameState gameState = gameOrganizer.currentGame().copy();
        Result<GameState, String> result = gameOrganizer.getGameMover().movePiece(movement, gameState);
        Board boardResult=result.key().getBoard().copy();
        Color enemyColor = gameOrganizer.currentGame().currentTurn() == Color.WHITE ? Color.BLACK : Color.WHITE;
        for (Piece p: boardResult.getPieces().values()) {
            if (p.getColor() == enemyColor) return new WinResult(false, null);
        }
        return new WinResult(true, gameOrganizer.currentGame().currentTurn());
    }
}
