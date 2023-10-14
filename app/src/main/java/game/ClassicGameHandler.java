package game;

import enums.Color;
import exception.InvalidTurnException;
import exception.PlayerNotExistsException;
import game.interfaces.Game;
import game.interfaces.GameHandler;
import game.interfaces.GameMover;
import game.interfaces.TurnHandler;
import piece.Movement;

public class ClassicGameHandler implements GameHandler {
    private final Game game;

    private final GameMover gameMover;

    private final TurnHandler turnHandler;

    public ClassicGameHandler(Game game, GameMover gameMover, TurnHandler turnHandler) {
        this.game = game;
        this.gameMover = gameMover;
        this.turnHandler = turnHandler;
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

    public GameHandler tryMovement(Movement movement,Game game){
        Color playerColor=turnHandler.getCurrentTurn();
        for (Player p: game.getPlayers()) {
            if (p.getColor().compareTo(playerColor)==0){
                if (isPlayerColorEqualsPieceColor(movement, game, playerColor)){
                    Game currentGame=game.copy();
                    Game gameResult= gameMover.movePiece(movement,currentGame);
                    return new ClassicGameHandler(gameResult,gameMover,turnHandler.nextTurn());
                }
                throw new InvalidTurnException("It's turn of "+playerColor+" player.");
            }
        }
        throw new PlayerNotExistsException("Player with color "+playerColor+" doesn't exists.");
    }

    @Override
    public GameHandler copy() {
        return new ClassicGameHandler(game.copy(),this.gameMover,turnHandler.nextTurn());
    }

    private boolean isPlayerColorEqualsPieceColor(Movement movement, Game game, Color playerColor) {
        return game.getBoard().getPieces().get(movement.getFrom()).getColor() == playerColor;
    }
}
