package game;

import board.Position;
import board.interfaces.Board;
import game.interfaces.Game;
import game.interfaces.GameMover;
import piece.Movement;
import piece.Piece;

public class ClassicGameMover implements GameMover {

    // TODO This should return a move result.

    @Override
    public Game movePiece(Movement movement, Game game) {
        Piece piece=getPieceInPosition(game.getBoard(),movement.getFrom());
        return null;
    }


    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }
}
