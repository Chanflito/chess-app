package checkers.game;

import common.game.ClassicGameState;
import common.game.Position;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import common.result.MoveResult;
import common.result.Result;
import common.board.interfaces.Board;
import common.game.interfaces.GameState;
import common.game.interfaces.GameMover;

import java.util.Optional;

public class CheckersGameMover implements GameMover {
    @Override
    public Result<GameState, String> movePiece(Movement movement, GameState gameState) {
        Piece piece=getPieceInPosition(gameState.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board boardClone= gameState.getBoard().copy();
        Result<Board,Boolean> result=moveHandler.handleMove(movement,boardClone);
        Optional<Boolean> success=result.value();
        if (success.isPresent() && success.get()){
            Board resultBoard=result.key().copy();
            resultBoard.getHistory().add(result.key().getPieces());
            return new MoveResult<>(new ClassicGameState(gameState.getPlayers(),resultBoard, gameState.currentTurn()),Optional.empty());
        }
        return new MoveResult<>(gameState.copy(), Optional.of("Invalid Movement"));
    }


    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }


}

