package checkers.game;

import common.game.ClassicGameData;
import common.game.Position;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import common.result.MoveResult;
import common.result.Result;
import common.board.interfaces.Board;
import common.game.interfaces.GameData;
import common.game.interfaces.GameMover;

import java.util.Optional;

public class CheckersGameMover implements GameMover {
    @Override
    public Result<GameData, String> movePiece(Movement movement, GameData gameData) {
        Piece piece=getPieceInPosition(gameData.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board boardClone= gameData.getBoard().copy();
        Result<Board,Boolean> result=moveHandler.handleMove(movement,boardClone);
        Optional<Boolean> success=result.value();
        if (success.isPresent() && success.get()){
            Board resultBoard=result.key().copy();
            resultBoard.getHistory().add(result.key().getPieces());
            return new MoveResult<>(new ClassicGameData(gameData.getPlayers(),resultBoard),Optional.empty());
        }
        return new MoveResult<>(gameData.copy(), Optional.of("Invalid Movement"));
    }


    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }


}

