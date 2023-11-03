package chess.game;

import common.game.Position;
import common.board.interfaces.Board;
import common.game.interfaces.Game;
import common.game.interfaces.GameMover;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import chess.result.MoveResult;
import chess.result.Result;

import java.util.List;
import java.util.Map;


public class ClassicGameMover implements GameMover {

    @Override
    public MoveResult<Game, String> movePiece(Movement movement, Game game) {
        Piece piece=getPieceInPosition(game.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board boardClone=game.getBoard().copy();
        Result<Board,Boolean> result= moveHandler.handleMove(movement, boardClone);
        if (result.getValue().get()){
            Board resultBoard=result.getKey().copy();
            resultBoard.getHistory().add(boardClone.getPieces());
            return new MoveResult<>(new ClassicGame(game.getPlayers(),resultBoard),null);
        }
        return new MoveResult<>(game.copy(),"Invalid Movement");
    }

    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }
}
