package checkers;

import checkers.helper.TurnHelper;
import common.game.Player;
import common.game.Position;
import common.move.Movement;
import common.game.Piece;
import common.result.InvalidMoveResult;
import common.result.MoveResult;
import common.result.Result;
import common.enums.Color;
import common.game.interfaces.*;
import common.result.WinResult;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CheckersGameOrganizer implements GameOrganizer {
    private final Game game;

    private final GameMover gameMover;

    private final TurnHandler turnHandler;


    private final GameOverCondition gameOverCondition;


    public CheckersGameOrganizer(Game game, GameMover gameMover, TurnHandler turnHandler, GameOverCondition gameOverCondition) {
        this.game = game;
        this.gameMover = gameMover;
        this.turnHandler = turnHandler;
        this.gameOverCondition = gameOverCondition;
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
    public Result<?,?> move(Movement movement, Game game){
        Color playerColor=turnHandler.getCurrentTurn();
        if (!existsPiece(movement, game)){
            return new InvalidMoveResult(true,"There is no piece in that position.");}
        Result<Boolean,Color> isWinner= isGameOver(movement);
        if (isWinner.getKey()){
            return new WinResult(true, isWinner.getValue().get());
        }
        MoveResult<GameOrganizer,String> moveResult= checkConditions(movement, game, playerColor);
        if (moveResult.getValue().isPresent()){
            return new InvalidMoveResult(true, moveResult.getValue().get());
        }
        return new MoveResult<>(moveResult.getKey(), Optional.empty());
    }

    @Override
    public Result<Boolean, Color> isGameOver(Movement movement) {
        return gameOverCondition.evaluateCondition(movement, this);
    }

    @Override
    public GameOrganizer copy() {
        return new CheckersGameOrganizer(this.game.copy(), this.gameMover, this.turnHandler, this.gameOverCondition);
    }

    private boolean isPlayerColorEqualsPieceColor(Movement movement, Game game, Color playerColor) {
        return findPiece(movement, game).getColor() == playerColor;
    }
    private Result<Game, String> makeMovement(Movement movement, Game currentGame) {
        return this.getGameMover().movePiece(movement, currentGame);
    }

    private Boolean existsPiece(Movement movement, Game game) {
        return game.getBoard().getPieces().containsKey(movement.getFrom());
    }

    private Piece findPiece(Movement movement, Game game) {
        return game.getBoard().getPieces().get(movement.getFrom());
    }

    @NotNull
    private MoveResult<GameOrganizer, String> checkConditions(Movement movement, Game game, Color playerColor) {
        for (Player p: game.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, game, playerColor)){
                    return evaluateMovement(movement, game);

                }
                return new MoveResult<>(this, "It's turn of " + playerColor + " player.");
            }
        }
        return new MoveResult<>(this, "Player with color " + playerColor + " doesn't exists.");
    }

    @NotNull
    private MoveResult<GameOrganizer, String> evaluateMovement(Movement movement, Game game) {
        Game currentGame= game.copy();
        Result<Game,String> gameResult= makeMovement(movement, currentGame);
        return isLegitMove(gameResult) ?
                getResultOfLegitMove(gameResult, movement.getTo()) :
                getResultOfNotLegitMove(gameResult);
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfNotLegitMove(Result<Game, String> gameResult) {
        return new MoveResult<>(new CheckersGameOrganizer(gameResult.getKey(), gameMover, turnHandler, this.gameOverCondition)
                , gameResult.getValue().get());
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfLegitMove(Result<Game, String> gameResult, Position finalPosition){
        TurnHelper turnHelper=new TurnHelper(finalPosition,gameResult.getKey().getBoard().copy());
        TurnHandler nextTurn= new CheckersTurnHandler(this.turnHandler.getCurrentTurn(),turnHelper).nextTurn();
        return new MoveResult<>(new
                CheckersGameOrganizer(gameResult.getKey(), gameMover, nextTurn, this.gameOverCondition), null);
    }

    private boolean isLegitMove(Result<Game, String> gameResult) {

        return gameResult.getValue().isEmpty();
    }
}
