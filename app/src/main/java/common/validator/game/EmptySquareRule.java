package common.validator.game;

import common.board.interfaces.Board;
import common.game.interfaces.GameState;
import common.move.Movement;
import common.result.Result;
import common.result.ValidatorResult;
import common.validator.interfaces.GameRule;

import java.util.Optional;

public class EmptySquareRule implements GameRule {
    @Override
    public Result<Boolean, String> isValid(Movement move, GameState gameState) {
        return isEmptySquare(move, gameState.getBoard()) ?
                new ValidatorResult<>(true, Optional.empty()) :
                new ValidatorResult<>(false, Optional.of("There is no piece in the square"));
    }

    private boolean isEmptySquare(Movement move, Board board){
        return board.getPieces().containsKey(move.getFrom());
    }
}
