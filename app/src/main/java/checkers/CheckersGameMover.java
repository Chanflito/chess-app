package checkers;

import common.game.Position;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import chess.result.Result;
import common.board.interfaces.Board;
import common.game.interfaces.Game;
import common.game.interfaces.GameMover;

public class CheckersGameMover implements GameMover {
    @Override
    public Result<Game, String> movePiece(Movement movement, Game game) {
        Piece piece=getPieceInPosition(game.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board previousBoard=game.getBoard().copy();
        return null;
    }

    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }

}

