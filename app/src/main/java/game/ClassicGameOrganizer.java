package game;

import enums.Color;
import game.interfaces.*;
import piece.Movement;
import piece.Piece;
import result.MoveResult;
import result.Result;

public class ClassicGameOrganizer implements GameOrganizer {

    private final Game game;

    private final GameMover gameMover;

    private final TurnHandler turnHandler;


    private final GameOverCondition gameOverCondition;

    public ClassicGameOrganizer(Game game, GameMover gameMover, TurnHandler turnHandler, GameOverCondition gameOverCondition) {
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
    public Result<Boolean, Color> isGameOver(Movement movement) {
        //Ver inmutabilidad aca.
        return this.gameOverCondition.evaluateCondition(movement, this);
    }

    @Override
    public GameOrganizer copy() {
        return new ClassicGameOrganizer(game.copy(),this.gameMover,turnHandler.nextTurn(),this.gameOverCondition);
    }

    @Override
    public GameOverCondition getGameOverCondition() {
        return this.gameOverCondition;
    }

    @Override
    public MoveResult<GameOrganizer,String> tryMovement(Movement movement, Game game){
        Color playerColor=turnHandler.getCurrentTurn();
        if (findPiece(movement, game) ==null){
            return new MoveResult<>(this,"There is no piece in that position.");}

        for (Player p: game.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, game, playerColor)){
                    Game currentGame=game.copy();
                    Result<Game,String> gameResult= makeMovement(movement, currentGame);
                    return gameResult.getValue().isEmpty() ?
                            new MoveResult<>(new
                                    ClassicGameOrganizer(gameResult.getKey(), gameMover, turnHandler.nextTurn(),this.gameOverCondition),null):
                                    new MoveResult<>(new ClassicGameOrganizer(gameResult.getKey(), gameMover, turnHandler, this.gameOverCondition),gameResult.getValue().get());

                }
                return new MoveResult<>(this,"It's turn of "+playerColor+" player.");
            }
        }
        return new MoveResult<>(this, "Player with color "+playerColor+" doesn't exists.");
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
