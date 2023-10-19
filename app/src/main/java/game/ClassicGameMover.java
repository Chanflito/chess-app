package game;

import board.Position;
import board.interfaces.Board;
import game.interfaces.Game;
import game.interfaces.GameMover;
import piece.Movement;
import piece.Piece;
import piece.interfaces.MoveHandler;
import result.MoveResult;
import result.Result;

import java.util.List;
import java.util.Map;
import java.util.Optional;
//Para el caso del enroque no deberia ser valido un movimiento, solamente el enroque deberia ser valido.
//Para el caso de la promocion,es valido el movimiento y la promocion.
//El jaque no se considera un movimiento especial, sino una condicion para que termine el juego.
public class ClassicGameMover implements GameMover {

    @Override
    public MoveResult<Game, String> movePiece(Movement movement, Game game) {
        Piece piece=getPieceInPosition(game.getBoard(),movement.getFrom());
        MoveHandler moveHandler=piece.getMoveHandler();
        Board previousBoard=game.getBoard().copy();
        Result<Board,Boolean> result=checkSpecialValidatorsAndAndValidators(movement,game,moveHandler);
        if (result.getValue().get()){
            Board resultCloneBoard=result.getKey().copy();
            resultCloneBoard.getHistory().add(previousBoard.getPieces());
            return new MoveResult<>(new ClassicGame(game.getPlayers(),resultCloneBoard),null);
        }
        if (checkValidators(movement, game, moveHandler)){
            return new MoveResult<>(makeMovement(movement, game),null);
        }
        return new MoveResult<>(game,"Invalid Movement");
    }
    private Result<Board,Boolean> checkSpecialValidatorsAndAndValidators(Movement movement, Game game,MoveHandler moveHandler){
        Result<Board,Boolean> result=moveHandler.checkSpecialValidators(movement, game.getBoard().copy());
        if (result.getValue().isPresent()){
            if (result.getValue().get() && moveHandler.checkAndValidators(movement, game.getBoard())){
                return result;
            }
        }
        return new MoveResult<>(game.getBoard(),false);
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
