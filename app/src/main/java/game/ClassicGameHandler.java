package game;

import enums.Color;
import game.interfaces.Game;
import game.interfaces.GameHandler;
import game.interfaces.GameMover;
import game.interfaces.TurnHandler;
import org.jetbrains.annotations.NotNull;
import piece.Movement;
import result.MoveResult;
import result.Result;

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

    public MoveResult<GameHandler,String> tryMovement(Movement movement,Game game){
        Color playerColor=turnHandler.getCurrentTurn();
        for (Player p: game.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, game, playerColor)){
                    Game currentGame=game.copy();
                    Result<Game,String> gameResult= makeMovement(movement, currentGame);
                    return gameResult.getValue()==null ?
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
                    new Player(Color.valueOf(gameResult.getValue()))),null);
        }
        return new MoveResult<>(this, gameResult.getValue());
    }

    private boolean isWinner(Result<Game, String> gameResult) {
        return gameResult.getValue().equals(Color.WHITE.toString()) || gameResult.getValue().equals(Color.BLACK.toString());
    }

    @Override
    public GameHandler copy() {
        return new ClassicGameHandler(game.copy(),this.gameMover,turnHandler.nextTurn());
    }

    private boolean isPlayerColorEqualsPieceColor(Movement movement, Game game, Color playerColor) {
        return game.getBoard().getPieces().get(movement.getFrom()).getColor() == playerColor;
    }
    private Result<Game, String> makeMovement(Movement movement, Game currentGame) {
        return this.getGameMover().movePiece(movement, currentGame);
    }
}
