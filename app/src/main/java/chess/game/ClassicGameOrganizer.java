package chess.game;

import common.enums.Color;
import common.game.ClassicGameState;
import common.game.interfaces.*;
import common.move.Movement;
import common.result.InvalidMoveResult;
import common.result.MoveResult;
import common.result.Result;
import common.result.WinResult;
import common.validator.interfaces.GameRule;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ClassicGameOrganizer implements GameOrganizer {

    private final GameState gameState;
    private final GameMover gameMover;

    private final TurnStrategy turnStrategy;

    private final GameOverCondition gameOverCondition;

    private final GameRule gameRule;

    public ClassicGameOrganizer(GameState gameState, GameMover gameMover, TurnStrategy turnStrategy,
                                GameOverCondition gameOverCondition, GameRule gameRule) {
        this.gameState = gameState;
        this.gameMover = gameMover;
        this.turnStrategy = turnStrategy;
        this.gameOverCondition = gameOverCondition;
        this.gameRule = gameRule;
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
        return new ClassicGameOrganizer(gameState.copy(),this.gameMover,getTurnHandler(),this.gameOverCondition,this.gameRule);
    }

    @Override
    public Result<?,?> move(Movement movement, GameState gameState){
        if (!isValidGameRule(movement, gameState)){
            return new InvalidMoveResult(true, getMessage(movement, gameState));
        }
        Result<Boolean,Color> isWinner= isGameOver(movement);
        if (isWinner.key()){
            return new WinResult(true, isWinner.value().get());
        }
        MoveResult<GameOrganizer,String> moveResult= evaluateMovement(movement, gameState);
        if (moveResult.value().isPresent()){
            return new InvalidMoveResult(true, moveResult.value().get());
        }
        return new MoveResult<>(moveResult.key(), Optional.empty());
    }

    private Boolean isValidGameRule(Movement movement, GameState gameState) {
        return gameRule.isValid(movement, gameState).key();
    }

    @NotNull
    private String getMessage(Movement movement, GameState gameState) {
        return gameRule.isValid(movement, gameState).value().get();
    }


    private Result<GameState, String> makeMovement(Movement movement, GameState currentGameState) {
        return this.getGameMover().movePiece(movement, currentGameState);
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
        return new MoveResult<>(new ClassicGameOrganizer(gameResult.key(), gameMover, turnStrategy, this.gameOverCondition, this.gameRule)
                , Optional.of(gameResult.value().get()));
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfLegitMove(Result<GameState, String> gameResult) {
        Color playerTurn=getTurnHandler().nextTurn(gameResult.key().copy());
        GameState gameAfterMove= new ClassicGameState(gameResult.key().getPlayers(),
                gameResult.key().getBoard().copy(),playerTurn);
        return new MoveResult<>(new
                ClassicGameOrganizer(gameAfterMove, gameMover, getTurnHandler() , this.gameOverCondition,this.gameRule), Optional.empty());
    }

    private boolean isLegitMove(Result<GameState, String> gameResult) {
        return gameResult.value().isEmpty();
    }
}
