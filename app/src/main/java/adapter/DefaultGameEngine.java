package adapter;

import director.BoardDirector;
import edu.austral.dissis.chess.gui.*;
import enums.Color;
import game.*;
import game.interfaces.GameHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Stack;

public class DefaultGameEngine implements GameEngine {

    private final GameHandler gameHandler;
    private final Stack<GameHandler> previousGameHandlers=new Stack<>();
    public DefaultGameEngine() {
        BoardDirector boardDirector = new BoardDirector();
        this.gameHandler= createClassicGame(boardDirector);
        previousGameHandlers.push(gameHandler);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
            result.Result<GameHandler,String> tryMovement=previousGameHandlers.peek().tryMovement(Adapter.convertMove(move)
                    ,previousGameHandlers.peek().currentGame());
            if (tryMovement.getValue()!=null){
                return new InvalidMove(tryMovement.getValue());
            }
            if (tryMovement.getKey().getWinner()!=null){
                return new GameOver(Adapter.getWinner(tryMovement.getKey().getWinner()));
            }
            previousGameHandlers.pop();
            previousGameHandlers.push(tryMovement.getKey());
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
        return new InitialState(Adapter.getBoardSize(gameHandler.currentGame().getBoard()),
                Adapter.getCurrentPieces(gameHandler.currentGame().getBoard()),
                Adapter.getCurrentTurn(gameHandler.getTurnHandler()));
    }

    @NotNull
    private static ClassicGameHandler createClassicGame(BoardDirector boardDirector) {
        return new ClassicGameHandler(new ClassicGame(List.of(new Player(Color.WHITE), new Player(Color.BLACK))
                , boardDirector.createClassicBoard(8, 8)), new ClassicGameMover(),
                new ClassicTurnHandler(Color.WHITE));
    }
}
