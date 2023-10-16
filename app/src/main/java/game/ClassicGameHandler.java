package game;

import enums.Color;
import game.interfaces.Game;
import game.interfaces.GameHandler;
import game.interfaces.GameMover;
import game.interfaces.TurnHandler;
import org.jetbrains.annotations.NotNull;
import piece.Movement;
import piece.Piece;
import result.MoveResult;
import result.Result;

import java.util.Optional;

public class ClassicGameHandler implements GameHandler {
    private final Game game;

    private final GameMover gameMover;

    private final TurnHandler turnHandler;

    private Player winner;
    public ClassicGameHandler(Game game, GameMover gameMover, TurnHandler turnHandler) {
        this.game = game;
        this.gameMover = gameMover;
        this.turnHandler = turnHandler;
    }

    private ClassicGameHandler(Game game,GameMover gameMover,TurnHandler turnHandler,Player winner){
        this.game=game;
        this.gameMover=gameMover;
        this.turnHandler=turnHandler;
        this.winner=winner;
    }
    @Override
    public Game currentGame() {
        return this.game;
    }

    @Override
    public GameMover getGameMover() {
        return this.gameMover;
    }

    @Override
    public TurnHandler getTurnHandler() {
        return this.turnHandler;
    }

    @Override
    public Player getWinner() {
        return this.winner;
    }

    @Override
    public GameHandler copy() {
        return new ClassicGameHandler(game.copy(),this.gameMover,turnHandler.nextTurn());
    }
    @Override
    public MoveResult<GameHandler,String> tryMovement(Movement movement,Game game){
        Color playerColor=turnHandler.getCurrentTurn();
        if (findPiece(movement, game) ==null){
            return new MoveResult<>(this,"There is no piece in that position.");}

        for (Player p: game.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, game, playerColor)){
                    Game currentGame=game.copy();
                    Result<Game,String> gameResult= makeMovement(movement, currentGame);
                    return gameResult.getValue().isEmpty() ?
                            new MoveResult<>(new ClassicGameHandler(gameResult.getKey(), gameMover, turnHandler.nextTurn())
                                    , null): finalResult(gameResult);

                }
                return new MoveResult<>(this,"It's turn of "+playerColor+" player.");
            }
        }
        return new MoveResult<>(this, "Player with color "+playerColor+" doesn't exists.");
    }



    @NotNull
    private MoveResult<GameHandler, String> finalResult(Result<Game, String> gameResult) {
        if (isWinner(gameResult)){
            return new MoveResult<>(new ClassicGameHandler(this.game,gameMover,this.turnHandler,
                    new Player(Color.valueOf(gameResult.getValue().get()))),
                    null);
        }
        return new MoveResult<>(this, gameResult.getValue().get());
    }

    private boolean isWinner(Result<Game, String> gameResult) {
        return gameResult.getValue().get().equals(Color.WHITE.toString())
                || gameResult.getValue().get().equals(Color.BLACK.toString());
    }



    private boolean isPlayerColorEqualsPieceColor(Movement movement, Game game, Color playerColor) {
        return findPiece(movement, game).getColor() == playerColor;
    }
    private Result<Game, String> makeMovement(Movement movement, Game currentGame) {
        return this.getGameMover().movePiece(movement, currentGame);
    }

    private Piece findPiece(Movement movement, Game game) {
        return game.getBoard().getPieces().get(movement.getFrom());
    }
}
