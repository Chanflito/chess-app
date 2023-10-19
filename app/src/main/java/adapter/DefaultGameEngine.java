package adapter;

import director.BoardDirector;
import edu.austral.dissis.chess.gui.*;
import enums.Color;
import game.*;
import game.interfaces.GameOrganizer;
import org.jetbrains.annotations.NotNull;
import result.Result;

import java.util.List;
import java.util.Stack;

public class DefaultGameEngine implements GameEngine {

    private final GameOrganizer gameOrganizer;
    private final Stack<GameOrganizer> previousGameOrganizers =new Stack<>();
    public DefaultGameEngine() {
        BoardDirector boardDirector = new BoardDirector();
        this.gameOrganizer = createCustomGame(boardDirector);
        previousGameOrganizers.push(gameOrganizer);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
            result.Result<GameOrganizer,String> tryMovement= previousGameOrganizers.peek().tryMovement(Adapter.convertMove(move)
                    , previousGameOrganizers.peek().currentGame());
            if (tryMovement.getValue().isPresent()){
                return new InvalidMove(tryMovement.getValue().get());
            }
            Result<Boolean,Color> isWinner= previousGameOrganizers.peek().isGameOver(Adapter.convertMove(move));
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

    @NotNull
    private static ClassicGameOrganizer createClassicGame(BoardDirector boardDirector) {
        return new ClassicGameOrganizer(new ClassicGame(List.of(new Player(Color.WHITE), new Player(Color.BLACK))
                , boardDirector.createClassicBoard(8, 8)), new ClassicGameMover(),
                new ClassicTurnHandler(Color.WHITE), new ClassicWinCondition());
    }
    private static ClassicGameOrganizer createCustomGame(BoardDirector boardDirector) {
        return new ClassicGameOrganizer(new ClassicGame(List.of(new Player(Color.WHITE), new Player(Color.BLACK))
                , boardDirector.createCustomChess(8, 8)), new ClassicGameMover(),
                new ClassicTurnHandler(Color.WHITE), new ClassicWinCondition());
    }
}
