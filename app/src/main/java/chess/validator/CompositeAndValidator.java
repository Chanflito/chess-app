package chess.validator;

import common.board.interfaces.Board;
import chess.piece.Movement;
import common.validator.interfaces.MovementValidator;

import java.util.List;

public class CompositeAndValidator implements MovementValidator{
    private final List<MovementValidator> validators;

    public CompositeAndValidator(List<MovementValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(Movement movement, Board board) {
        for (MovementValidator m: validators) {
            if (!m.isValid(movement,board)){
                return false;
            }
        }
         return true;
        }
    }

