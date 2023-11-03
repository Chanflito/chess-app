package checkers;

import chess.piece.Movement;
import chess.piece.Piece;
import chess.result.MoveResult;
import chess.result.Result;
import common.enums.Color;
import common.game.interfaces.*;

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
    public MoveResult<GameOrganizer, String> move(Movement movement, Game game) {
        return null;
    }

    @Override
    public Result<Boolean, Color> isGameOver(Movement movement) {
        return null;
    }

    @Override
    public GameOrganizer copy() {
        return null;
    }

    @Override
    public GameOverCondition getGameOverCondition() {
        return this.gameOverCondition;
    }

    private Piece findPiece(Movement movement, Game game) {
        return game.getBoard().getPieces().get(movement.getFrom());
    }
}
