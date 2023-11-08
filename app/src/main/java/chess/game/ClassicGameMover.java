package chess.game;

import common.game.ClassicGameState;
import common.game.Position;
import common.board.interfaces.Board;
import common.game.interfaces.GameState;
import common.game.interfaces.GameMover;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import common.result.MoveResult;
import common.result.Result;

import java.util.Optional;


public class ClassicGameMover implements GameMover {

    @Override
    public MoveResult<GameState, String> movePiece(Movement movement, GameState gameState) {
        Piece piece=getPieceInPosition(gameState.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board boardClone= gameState.getBoard().copy();
        Result<Board,Boolean> result= moveHandler.handleMove(movement, boardClone);
        if (result.value().get()){
            Board resultBoard=result.key().copy();
            resultBoard.getHistory().add(boardClone.getPieces());
            return new MoveResult<>(new ClassicGameState(gameState.getPlayers(),resultBoard, gameState.currentTurn()), Optional.empty());
        }
        return new MoveResult<>(gameState.copy(),Optional.of("Invalid Movement"));
    }

    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }
}
