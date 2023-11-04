package common.wincondition;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Piece;
import common.game.interfaces.Game;
import common.game.interfaces.GameOrganizer;
import common.game.interfaces.GameOverCondition;
import common.move.Movement;
import common.result.Result;
import common.result.WinResult;


public class AllEnemyPiecesCaptureCondition implements GameOverCondition {
    @Override
    public Result<Boolean, Color> evaluateCondition(Movement movement, GameOrganizer gameOrganizer) {
        Board board = gameOrganizer.currentGame().getBoard();
        Game game = gameOrganizer.currentGame().copy();
        Result<Game, String> result = gameOrganizer.getGameMover().movePiece(movement, game);
        Board boardResult=result.getKey().getBoard().copy();
        Color enemyColor = gameOrganizer.getTurnHandler().getCurrentTurn() == Color.WHITE ? Color.BLACK : Color.WHITE;
        for (Piece p: boardResult.getPieces().values()) {
            if (p.getColor() == enemyColor) return new WinResult(false, null);
        }
        return new WinResult(true, gameOrganizer.getTurnHandler().getCurrentTurn());
    }
}
