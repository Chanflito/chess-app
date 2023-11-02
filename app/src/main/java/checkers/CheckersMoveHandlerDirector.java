package checkers;


import chess.piece.ClassicMoveHandler;
import chess.piece.interfaces.MoveHandler;
import common.enums.Color;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;

import java.util.ArrayList;
import java.util.List;

public class CheckersMoveHandlerDirector {

    public MoveHandler createPawnMovement(Color color){
        int direction= color==Color.WHITE?1:-1;
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setPawnsValidator(direction, orValidator, andValidator,pieceMovers);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }

    private void setPawnsValidator(int direction, List<MovementValidator> orValidator,
                                   List<MovementValidator> andValidator, List<PieceMover> pieceMovers) {

    }


}
