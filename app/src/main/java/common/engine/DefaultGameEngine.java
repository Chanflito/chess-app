package common.engine;

import checkers.CheckersGameOrganizer;
import checkers.CheckersGameOrganizerBuilder;
import common.adapter.Adapter;
import chess.game.GameOrganizerBuilder;
import common.result.InvalidMoveResult;
import edu.austral.dissis.chess.gui.*;
import common.enums.Color;
import common.game.interfaces.GameOrganizer;
import org.jetbrains.annotations.NotNull;
import common.result.Result;

import java.util.Stack;
//Implementar una condicion de victoria nueva, haciendo un composicion.
//Hacerlo con horizontal y vertical, diagonal etc.
public class DefaultGameEngine implements GameEngine {

    private final GameOrganizer gameOrganizer;
    private final Stack<GameOrganizer> previousGameOrganizers =new Stack<>();
    public DefaultGameEngine() {
        this.gameOrganizer = CheckersGameOrganizerBuilder.createCheckersGame();
        previousGameOrganizers.push(gameOrganizer);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
            Result<?,?> tryMovement= previousGameOrganizers.peek().move(Adapter.convertMove(move)
                    , previousGameOrganizers.peek().currentGame());
            if (tryMovement instanceof InvalidMoveResult){
                return new InvalidMove((String) tryMovement.getValue().get());
            }
            if (tryMovement instanceof common.result.WinResult){
                return new GameOver(Adapter.getWinner((Color) tryMovement.getValue().get()));
            }
            GameOrganizer newGameOrganizer= (GameOrganizer) tryMovement.getKey();
            previousGameOrganizers.pop();
            previousGameOrganizers.push(newGameOrganizer);
            return new NewGameState(Adapter.getCurrentPieces(newGameOrganizer.currentGame().getBoard())
                    ,Adapter.getCurrentTurn(newGameOrganizer.getTurnHandler()));
    }

    @NotNull
    @Override
    public InitialState init() {
        return createInitialState();
    }

    @NotNull
    private InitialState createInitialState() {
        return new InitialState(Adapter.getBoardSize(gameOrganizer.currentGame().getBoard()),
                Adapter.getCurrentPieces(gameOrganizer.currentGame().getBoard()),
                Adapter.getCurrentTurn(gameOrganizer.getTurnHandler()));
    }

}
