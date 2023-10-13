package director;

import enums.Color;
import piece.ClassicMoveHandler;
import piece.interfaces.MoveHandler;
import validator.*;
import validator.interfaces.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class MoveDirector {

    //Falta revisar si hay piezas en el medio...
    public MoveHandler createPawnMovement(Color color){
        int direction= color==Color.WHITE?1:-1;
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(direction,0),new IncrementValidator(1),
                        new CaptureValidator(false))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(direction,-1),
                new IncrementValidator(1),new CaptureValidator(true))));
        orValidator.add( new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(direction,1),
                new IncrementValidator(1),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(direction,0),new IncrementValidator(2)
                ,new CaptureValidator(false),new QuantityMovementValidator(0))));
        andValidator.add(new InBoardValidator());
        return new ClassicMoveHandler(orValidator,andValidator);
    }
}
