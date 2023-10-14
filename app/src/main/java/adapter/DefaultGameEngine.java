package adapter;

import director.BoardDirector;
import edu.austral.dissis.chess.gui.*;
import enums.Color;
import game.*;
import game.interfaces.GameHandler;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class DefaultGameEngine implements GameEngine {

    private GameHandler gameHandler;
    
    public DefaultGameEngine() {
        BoardDirector boardDirector = new BoardDirector();
        this.gameHandler= createClassicGame(boardDirector);
    }


    //TODO: Add movement exceptions..
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        GameHandler tryMovement=gameHandler.tryMovement(Adapter.convertMove(move),gameHandler.currentGame());
        if (tryMovement!=null){
            return new NewGameState(Adapter.getCurrentPieces(tryMovement.currentGame().getBoard())
                    ,Adapter.getCurrentTurn(tryMovement.getTurnHandler()));
        }
        return new InvalidMove("No valid move");
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
