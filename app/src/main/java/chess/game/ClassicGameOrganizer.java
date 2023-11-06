package chess.game;

import common.enums.Color;
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

    private final GameData gameData;

    private final GameMover gameMover;

    private final TurnHandler turnHandler;


    private final GameOverCondition gameOverCondition;

    public ClassicGameOrganizer(GameData gameData, GameMover gameMover, TurnHandler turnHandler, GameOverCondition gameOverCondition) {
        this.gameData = gameData;
        this.gameMover = gameMover;
        this.turnHandler = turnHandler;
        this.gameOverCondition = gameOverCondition;
    }

    @Override
    public GameData currentGame() {
        return this.gameData;
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
    public Result<Boolean, Color> isGameOver(Movement movement) {
        return this.gameOverCondition.evaluateCondition(movement, this);
    }

    @Override
    public GameOrganizer copy() {
        return new ClassicGameOrganizer(gameData.copy(),this.gameMover,turnHandler.nextTurn(),this.gameOverCondition);
    }

    @Override
    public Result<?,?> move(Movement movement, GameData gameData){
        Color playerColor=turnHandler.getCurrentTurn();
        if (!existsPiece(movement, gameData)){
            return new InvalidMoveResult(true,"There is no piece in that position.");}
        Result<Boolean,Color> isWinner= isGameOver(movement);
        if (isWinner.key()){
            return new WinResult(true, isWinner.value().get());
        }
        MoveResult<GameOrganizer,String> moveResult= checkConditions(movement, gameData, playerColor);
        if (moveResult.value().isPresent()){
            return new InvalidMoveResult(true, moveResult.value().get());
        }
        return new MoveResult<>(moveResult.key(), Optional.empty());
    }


    private boolean isPlayerColorEqualsPieceColor(Movement movement, GameData gameData, Color playerColor) {
        return findPiece(movement, gameData).getColor() == playerColor;
    }
    private Result<GameData, String> makeMovement(Movement movement, GameData currentGameData) {
        return this.getGameMover().movePiece(movement, currentGameData);
    }

    private Boolean existsPiece(Movement movement, GameData gameData) {
        return gameData.getBoard().getPieces().containsKey(movement.getFrom());
    }

    private Piece findPiece(Movement movement, GameData gameData) {
        return gameData.getBoard().getPieces().get(movement.getFrom());
    }

    @NotNull
    private MoveResult<GameOrganizer, String> checkConditions(Movement movement, GameData gameData, Color playerColor) {
        for (Player p: gameData.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, gameData, playerColor)){
                    return evaluateMovement(movement, gameData);

                }
                return new MoveResult<>(this, Optional.of("It's turn of " + playerColor + " player."));
            }
        }
        return new MoveResult<>(this, Optional.of("Player with color " + playerColor + " doesn't exists."));
    }

    @NotNull
    private MoveResult<GameOrganizer, String> evaluateMovement(Movement movement, GameData gameData) {
        GameData currentGameData = gameData.copy();
        Result<GameData,String> gameResult= makeMovement(movement, currentGameData);
        return isLegitMove(gameResult) ?
                getResultOfLegitMove(gameResult) :
                getResultOfNotLegitMove(gameResult);
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfNotLegitMove(Result<GameData, String> gameResult) {
        return new MoveResult<>(new ClassicGameOrganizer(gameResult.key(), gameMover, turnHandler, this.gameOverCondition)
                , Optional.of(gameResult.value().get()));
    }

    @NotNull
    private MoveResult<GameOrganizer, String> getResultOfLegitMove(Result<GameData, String> gameResult) {
        return new MoveResult<>(new
                ClassicGameOrganizer(gameResult.key(), gameMover, turnHandler.nextTurn(), this.gameOverCondition), Optional.empty());
    }

    private boolean isLegitMove(Result<GameData, String> gameResult) {
        return gameResult.value().isEmpty();
    }
}
