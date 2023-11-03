package checkers;


import chess.piece.ClassicMoveHandler;
import chess.piece.mover.CompositeMover;
import chess.piece.mover.DefaultMover;
import chess.validator.CompositeAndValidator;
import chess.validator.CompositeOrValidator;
import common.move.MoveHandler;
import common.enums.Color;
import common.validator.interfaces.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class CheckersMoveHandlerDirector {

    public MoveHandler createPawnMovement(Color color){
        int direction= color==Color.WHITE?1:-1;
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> andValidators=new ArrayList<>();
        CompositeAndValidator andValidator=new CompositeAndValidator(andValidators);
        setPawnsValidator(direction,orValidators,andValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        //PieceMover promoteMover=createPromotion(direction);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }

    private void setPawnsValidator(int direction, List<MovementValidator> orValidator,
                                   List<MovementValidator> andValidator) {

    }



}
