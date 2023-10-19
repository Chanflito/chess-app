package director;

import enums.Color;
import piece.ClassicMoveHandler;
import piece.interfaces.MoveHandler;
import piece.mover.PromoteMover;
import validator.*;
import validator.interfaces.MovementValidator;
import validator.interfaces.PieceMover;

import java.util.ArrayList;
import java.util.List;

public class MoveDirector {
    public MoveHandler createPawnMovement(Color color){
        int direction= color==Color.WHITE?1:-1;
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setPawnsValidator(direction, orValidator, andValidator,pieceMovers);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }


    public MoveHandler createRookMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setRooksValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }
    public MoveHandler createBishopMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setBishopValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }

    public MoveHandler createQueenMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setQueenValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }

    public MoveHandler createKnightMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setKnightValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }

    public MoveHandler createKingMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setKingValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }
    private void setPawnsValidator(int direction,
                                          List<MovementValidator> orValidator, List<MovementValidator> andValidator,
                                   List<PieceMover> pieceMovers) {
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(direction,0),new IncrementValidator(1),
                        new PawnCaptureValidator(false))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(direction,-1),
                new IncrementValidator(1),new PawnCaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(direction,1),
                new IncrementValidator(1),new PawnCaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(direction,0),
                new IncrementValidator(2)
                ,new PawnCaptureValidator(false),new QuantityMovementValidator(0))));
        andValidator.add(new InBoardValidator());
        andValidator.add(new CheckValidator());
        pieceMovers.add(new PromoteMover(List.of(new CaptureValidator(false),
                new UnidirectionalMovementValidator(direction,0),new IncrementValidator(1))));
    }

    private void setRooksValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,0),
                        new PathValidator(1,0),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,0),new PathValidator(-1,0)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,1),new PathValidator(0,1)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,-1),new PathValidator(0,-1))
        ));
        addDefaultAndValidator(andValidator);

    }

    private void setBishopValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,1),new PathValidator(1,1),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,-1),new PathValidator(-1,-1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,1),new PathValidator(-1,1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,-1),new PathValidator(1,-1),new CaptureValidator(true))
        ));
        addDefaultAndValidator(andValidator);
    }

    private void setQueenValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,1),new PathValidator(1,1),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,-1),new PathValidator(-1,-1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,1),new PathValidator(-1,1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,-1),new PathValidator(1,-1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,0),new PathValidator(1,0),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,0),new PathValidator(-1,0),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,1),new PathValidator(0,1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,-1),new PathValidator(0,-1),new CaptureValidator(true))
        ));
        addDefaultAndValidator(andValidator);
    }

    private void setKnightValidator(List<MovementValidator> orValidator,List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(2,1),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(2,-1),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(-2,1),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(-2,-1),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(1,2),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(1,-2),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(-1,2),new CaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new ComposeMovementValidator(-1,-2),new CaptureValidator(true))));
        addDefaultAndValidator(andValidator);
    }

    private void setKingValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,1),new IncrementValidator(1),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,-1),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,1),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,-1),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(1,0),new IncrementValidator(1),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(-1,0),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,1),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(0,-1),new IncrementValidator(1),new CaptureValidator(true))
        ));
        addDefaultAndValidator(andValidator);
    }

    private static void addDefaultAndValidator(List<MovementValidator> andValidator){
        andValidator.add(new InBoardValidator());
        andValidator.add(new CheckValidator());
    }
}
