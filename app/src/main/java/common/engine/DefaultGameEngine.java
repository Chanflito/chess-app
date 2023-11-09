package common.engine;

import chess.game.GameOrganizerFactory;
import common.adapter.Adapter;
import edu.austral.dissis.chess.gui.*;
import common.game.interfaces.GameOrganizer;
import org.jetbrains.annotations.NotNull;
import common.result.Result;

import java.util.Stack;
public class DefaultGameEngine implements GameEngine {

    private final GameOrganizer gameOrganizer;
    private final Stack<GameOrganizer> previousGameOrganizers =new Stack<>();
    public DefaultGameEngine() {
        this.gameOrganizer = GameOrganizerFactory.createClassicGame();
        previousGameOrganizers.push(gameOrganizer);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Result<?, ?> tryMovement = makeMovement(move);
        return Adapter.convertResult(tryMovement,previousGameOrganizers);
    }

    private Result<?, ?> makeMovement(@NotNull Move move) {
        return previousGameOrganizers.peek().move(Adapter.convertMove(move)
                , previousGameOrganizers.peek().currentGame());
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
                Adapter.getCurrentTurn(gameOrganizer.currentGame().currentTurn()));
    }

}
