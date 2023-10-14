package director;

import enums.Color;
import piece.ClassicMoveHandler;
import piece.interfaces.MoveHandler;
import validator.*;
import validator.interfaces.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class MoveDirector {
    public MoveHandler createPawnMovement(Color color){
        int direction= color==Color.WHITE?1:-1;
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        setPawnsValidator(direction, orValidator, andValidator);
        return new ClassicMoveHandler(orValidator,andValidator);
    }


    public MoveHandler createRookMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        setRooksValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator);
    }
    public MoveHandler createBishopMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        setBishopValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator);
    }

    public MoveHandler createQueenMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        setQueenValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator);
    }

    public MoveHandler createKnightMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        setKnightValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator);
    }

    public MoveHandler createKingMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        setKingValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator);
    }
    private void setPawnsValidator(int direction,
                                          List<MovementValidator> orValidator, List<MovementValidator> andValidator) {
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
    }

    private void setRooksValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,0),new PathValidator(1,0)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,0),new PathValidator(-1,0))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,1),new PathValidator(0,1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,-1),new PathValidator(0,-1))
        ));
        addDefaultAndValidator(andValidator);

    }

    private void setBishopValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,1),new PathValidator(1,1)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,-1),new PathValidator(-1,-1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,1),new PathValidator(-1,1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,-1),new PathValidator(1,-1))
        ));
        andValidator.add(new InBoardValidator());
    }

    private void setQueenValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,1),new PathValidator(1,1)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,-1),new PathValidator(-1,-1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,1),new PathValidator(-1,1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,-1),new PathValidator(1,-1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,0),new PathValidator(1,0)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,0),new PathValidator(-1,0))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,1),new PathValidator(0,1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,-1),new PathValidator(0,-1))
        ));
        addDefaultAndValidator(andValidator);
    }

    private void setKnightValidator(List<MovementValidator> orValidator,List<MovementValidator> andValidator){
        orValidator.add(new ComposeMovementValidator(2,1));
        orValidator.add(new ComposeMovementValidator(2,-1));
        orValidator.add(new ComposeMovementValidator(-2,1));
        orValidator.add(new ComposeMovementValidator(-2,-1));
        orValidator.add(new ComposeMovementValidator(1,2));
        orValidator.add(new ComposeMovementValidator(1,-2));
        orValidator.add(new ComposeMovementValidator(-1,2));
        orValidator.add(new ComposeMovementValidator(-1,-2));
        addDefaultAndValidator(andValidator);
    }

    private void setKingValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,1),new IncrementValidator(1)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,-1),new IncrementValidator(1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,1),new IncrementValidator(1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,-1),new IncrementValidator(1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,0),new IncrementValidator(1)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,0),new IncrementValidator(1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,1),new IncrementValidator(1))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,-1),new IncrementValidator(1))
        ));
        addDefaultAndValidator(andValidator);
    }

    private static void addDefaultAndValidator(List<MovementValidator> andValidator){
        andValidator.add(new InBoardValidator());
        andValidator.add(new CaptureValidator(true));
    }
}
