package game;

import board.Position;
import board.interfaces.Board;
import edu.austral.dissis.chess.gui.Move;
import enums.Color;
import exception.InvalidMovementException;
import game.interfaces.Game;
import game.interfaces.GameMover;
import org.jetbrains.annotations.NotNull;
import piece.Movement;
import piece.Piece;
import piece.interfaces.MoveHandler;
import result.MoveResult;

import java.util.List;
import java.util.Map;

public class ClassicGameMover implements GameMover {

    @Override
    public MoveResult<Game, Color> movePiece(Movement movement, Game game) {
        Piece piece=getPieceInPosition(game.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        if (checkValidators(movement, game, moveHandler)){
            return new MoveResult<>(makeMovement(movement, game),null);
        }
        throw new InvalidMovementException("Invalid movement");
    }


    private ClassicGame makeMovement(Movement movement, Game game) {
        Board board= game.getBoard().copy();
        List<Map<Position,Piece>> history= board.getHistory();
        history.add(board.getPieces());
        Piece pieceToMove=board.getPieces().get(movement.getFrom());
        board.getPieces().remove(movement.getFrom());
        board.getPieces().put(movement.getTo().copy(),pieceToMove);
        return new ClassicGame(game.getPlayers(), board);
    }

    private boolean checkValidators(Movement movement, Game game, MoveHandler moveHandler) {
        return moveHandler.checkAndValidators(movement, game.getBoard())
                && moveHandler.checkOrValidators(movement, game.getBoard());
    }


    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }
}
