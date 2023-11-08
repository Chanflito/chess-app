package chess.game;

import common.enums.Color;
import common.game.ClassicGameState;
import common.game.Player;
import common.game.interfaces.*;
import common.move.Movement;
import common.game.Piece;
import common.result.InvalidMoveResult;
import common.result.MoveResult;
import common.result.Result;
import common.result.WinResult;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ClassicGameOrganizer implements GameOrganizer {

    private final GameState gameState;

    private final GameMover gameMover;

    private final TurnStrategy turnStrategy;

    private final GameOverCondition gameOverCondition;

    public ClassicGameOrganizer(GameState gameState, GameMover gameMover, TurnStrategy turnStrategy, GameOverCondition gameOverCondition) {
        this.gameState = gameState;
        this.gameMover = gameMover;
        this.turnStrategy = turnStrategy;
        this.gameOverCondition = gameOverCondition;
    }

    @Override
    public GameState currentGame() {
        return this.gameState;
    }

    @Override
    public GameMover getGameMover() {
        return this.gameMover;
    }

    @Override
    public TurnStrategy getTurnHandler() {
        return this.turnStrategy;
    }


    @Override
    public Result<Boolean, Color> isGameOver(Movement movement) {
        return this.gameOverCondition.evaluateCondition(movement, this);
    }

    @Override
    public GameOrganizer copy() {
        return new ClassicGameOrganizer(gameState.copy(),this.gameMover,getTurnHandler(),this.gameOverCondition);
    }

    @Override
    public Result<?,?> move(Movement movement, GameState gameState){
        Color playerColor= gameState.currentTurn();
        if (!existsPiece(movement, gameState)){
            return new InvalidMoveResult(true,"There is no piece in that position.");}
        Result<Boolean,Color> isWinner= isGameOver(movement);
        if (isWinner.key()){
            return new WinResult(true, isWinner.value().get());
        }
        MoveResult<GameOrganizer,String> moveResult= checkConditions(movement, gameState, playerColor);
        if (moveResult.value().isPresent()){
            return new InvalidMoveResult(true, moveResult.value().get());
        }
        return new MoveResult<>(moveResult.key(), Optional.empty());
    }


    private boolean isPlayerColorEqualsPieceColor(Movement movement, GameState gameState, Color playerColor) {
        return findPiece(movement, gameState).getColor() == playerColor;
    }
    private Result<GameState, String> makeMovement(Movement movement, GameState currentGameState) {
        return this.getGameMover().movePiece(movement, currentGameState);
    }

    private Boolean existsPiece(Movement movement, GameState gameState) {
        return gameState.getBoard().getPieces().containsKey(movement.getFrom());
    }

    private Piece findPiece(Movement movement, GameState gameState) {
        return gameState.getBoard().getPieces().get(movement.getFrom());
    }

    @NotNull
    private MoveResult<GameOrganizer, String> checkConditions(Movement movement, GameState gameState, Color playerColor) {
        for (Player p: gameState.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, gameState, playerColor)){
                    return evaluateMovement(movement, gameState);

                }
                return new MoveResult<>(this, Optional.of("It's turn of " + playerColor + " player."));
            }
        }
        return new MoveResult<>(this, Optional.of("Player with color " + playerColor + " doesn't exists."));
    }

    @NotNull
    private MoveResult<GameOrganizer, String> evaluateMovement(Movement movement, GameState gameState) {
        GameState currentGameState = gameState.copy();
        Result<GameState,String> gameResult= makeMovement(movement, currentGameState);
        return isLegitMove(gameResult) ?
                getResultOfLegitMove(gameResult) :
                getResultOfNotLegitMove(gameResult);
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfNotLegitMove(Result<GameState, String> gameResult) {
        return new MoveResult<>(new ClassicGameOrganizer(gameResult.key(), gameMover, turnStrategy, this.gameOverCondition)
                , Optional.of(gameResult.value().get()));
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfLegitMove(Result<GameState, String> gameResult) {
        Color playerTurn=getTurnHandler().nextTurn(gameResult.key().copy());
        GameState gameAfterMove= new ClassicGameState(gameResult.key().getPlayers(),
                gameResult.key().getBoard().copy(),playerTurn);
        return new MoveResult<>(new
                ClassicGameOrganizer(gameAfterMove, gameMover, getTurnHandler() , this.gameOverCondition), Optional.empty());
    }

    private boolean isLegitMove(Result<GameState, String> gameResult) {
        return gameResult.value().isEmpty();
    }
}
