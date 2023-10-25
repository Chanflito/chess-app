package checkers;

import chess.adapter.Adapter;
import chess.result.Result;
import common.enums.Color;
import common.game.interfaces.GameOrganizer;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class CheckersGameEngine implements GameEngine {
    private final GameOrganizer gameOrganizer;
    private final Stack<GameOrganizer> previousGameOrganizers =new Stack<>();
    public CheckersGameEngine() {
        this.gameOrganizer = CheckersGameOrganizerBuilder.createCheckersGame();
        previousGameOrganizers.push(gameOrganizer);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Result<GameOrganizer,String> tryMovement= previousGameOrganizers.peek().tryMovement(Adapter.convertMove(move)
                , previousGameOrganizers.peek().currentGame());
        if (tryMovement.getValue().isPresent()){
            return new InvalidMove(tryMovement.getValue().get());
        }
        Result<Boolean, Color> isWinner= previousGameOrganizers.peek().isGameOver(Adapter.convertMove(move));
        if (isWinner.getKey()){
            return new GameOver(Adapter.getWinner(isWinner.getValue().get()));
        }
        previousGameOrganizers.pop();
        previousGameOrganizers.push(tryMovement.getKey());
        return new NewGameState(Adapter.getCurrentPieces(tryMovement.getKey().currentGame().getBoard())
                ,Adapter.getCurrentTurn(tryMovement.getKey().getTurnHandler()));
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
