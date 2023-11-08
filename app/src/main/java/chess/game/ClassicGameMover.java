package chess.game;

import common.game.ClassicGameData;
import common.game.Position;
import common.board.interfaces.Board;
import common.game.interfaces.GameData;
import common.game.interfaces.GameMover;
import common.move.Movement;
import common.game.Piece;
import common.move.MoveHandler;
import common.result.MoveResult;
import common.result.Result;

import java.util.Optional;


public class ClassicGameMover implements GameMover {

    @Override
    public MoveResult<GameData, String> movePiece(Movement movement, GameData gameData) {
        Piece piece=getPieceInPosition(gameData.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board boardClone= gameData.getBoard().copy();
        Result<Board,Boolean> result= moveHandler.handleMove(movement, boardClone);
        if (result.value().get()){
            Board resultBoard=result.key().copy();
            resultBoard.getHistory().add(boardClone.getPieces());
            return new MoveResult<>(new ClassicGameData(gameData.getPlayers(),resultBoard,gameData.currentTurn()), Optional.empty());
        }
        return new MoveResult<>(gameData.copy(),Optional.of("Invalid Movement"));
    }

    private Piece getPieceInPosition(Board board, Position position){
        return board.getPieces().get(position);
    }
}
