package checkers;

import common.game.ClassicGame;
import common.game.Position;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import common.result.MoveResult;
import common.result.Result;
import common.board.interfaces.Board;
import common.game.interfaces.Game;
import common.game.interfaces.GameMover;

import java.util.Optional;

public class CheckersGameMover implements GameMover {
    @Override
    public Result<Game, String> movePiece(Movement movement, Game game) {
        Piece piece=getPieceInPosition(game.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board boardClone=game.getBoard().copy();
        Result<Board,Boolean> result=moveHandler.handleMove(movement,boardClone);
        Optional<Boolean> success=result.getValue();
        if (success.isPresent() && success.get()){
            Board resultBoard=result.getKey().copy();
            resultBoard.getHistory().add(result.getKey().getPieces());
            return new MoveResult<>(new ClassicGame(game.getPlayers(),resultBoard),null);
        }
        return new MoveResult<>(game.copy(),"Invalid Movement");
    }


    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }


}

