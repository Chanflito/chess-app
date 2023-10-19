package piece;

import board.interfaces.Board;
import piece.interfaces.MoveHandler;
import result.MoveResult;
import result.Result;
import validator.interfaces.MovementValidator;
import validator.interfaces.PieceMover;

import java.util.List;

public class ClassicMoveHandler implements MoveHandler {
    private final List<MovementValidator> orValidators;
    private final  List<MovementValidator> andValidators;

    private final List<PieceMover> specialValidators;

    public ClassicMoveHandler(List<MovementValidator> orValidators, List<MovementValidator> andValidators, List<PieceMover> specialValidators) {
        this.orValidators = orValidators;
        this.andValidators = andValidators;
        this.specialValidators = specialValidators;
    }

    @Override
    public boolean checkOrValidators(Movement movement, Board board) {
        for (MovementValidator m: orValidators) {
            if (m.isValid(movement,board)) return true;
        }
        return false;
    }
    @Override
    public boolean checkAndValidators(Movement movement, Board board) {
        for (MovementValidator m: andValidators) {
            if (m.isValid(movement,board)) return false;
        }
        return true;
    }

    @Override
    public Result<Board, Boolean> checkSpecialValidators(Movement movement, Board board) {
        if (!specialValidators.isEmpty()){
            for (PieceMover p: specialValidators){
                if (p.isValid(movement,board)){
                    return p.move(movement,board);
                }
                return new MoveResult<>(board,false);
            }
        }
        return new MoveResult<>(board,false);
    }
}
