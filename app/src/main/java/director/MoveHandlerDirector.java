package director;

import enums.Color;
import enums.Direction;
import piece.ClassicMoveHandler;
import piece.interfaces.MoveHandler;
import piece.mover.PromoteMover;
import validator.*;
import validator.interfaces.MovementValidator;
import validator.interfaces.PieceMover;

import java.util.ArrayList;
import java.util.List;

public class MoveHandlerDirector {
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

    public MoveHandler createArchBishopMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();
        setBishopValidator(orValidator,andValidator);
        setKnightValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }

    public MoveHandler createChancellorMovement(){
        List<MovementValidator> orValidator=new ArrayList<>();
        List<MovementValidator> andValidator=new ArrayList<>();
        List<PieceMover> pieceMovers=new ArrayList<>();

        setRooksValidator(orValidator,andValidator);
        setKnightValidator(orValidator,andValidator);
        return new ClassicMoveHandler(orValidator,andValidator,pieceMovers);
    }
    private void setPawnsValidator(int direction,
                                          List<MovementValidator> orValidator, List<MovementValidator> andValidator,
                                   List<PieceMover> pieceMovers) {
        List<Direction> getPawnsDirections=getPawnDirections(direction==1?Color.WHITE:Color.BLACK);
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(getPawnsDirections.get(0)),new IncrementValidator(1),
                        new PawnCaptureValidator(false))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(getPawnsDirections.get(1)),
                new IncrementValidator(1),new PawnCaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(getPawnsDirections.get(2)),
                new IncrementValidator(1),new PawnCaptureValidator(true))));
        orValidator.add(new CompositeAndValidator(List.of(new UnidirectionalMovementValidator(getPawnsDirections.get(0)),
                new IncrementValidator(2)
                ,new PawnCaptureValidator(false),new QuantityMovementValidator(0))));
        andValidator.add(new InBoardValidator());
        andValidator.add(new CheckValidator());
        pieceMovers.add(new PromoteMover(List.of(new CaptureValidator(false),
                new UnidirectionalMovementValidator(getPawnsDirections.get(0)),new IncrementValidator(1))));
    }

    private void setRooksValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP),
                        new PathValidator(Direction.UP),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN),new PathValidator(Direction.DOWN)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.RIGHT),new PathValidator(Direction.RIGHT)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.LEFT),new PathValidator(Direction.LEFT),
                        new CaptureValidator(true))));
        addDefaultAndValidator(andValidator);

    }

    private void setBishopValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_RIGHT),new PathValidator(Direction.UP_RIGHT),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_LEFT),new PathValidator(Direction.DOWN_LEFT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_LEFT),new PathValidator(Direction.UP_LEFT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_RIGHT),new PathValidator(Direction.DOWN_RIGHT),new CaptureValidator(true))
        ));
        addDefaultAndValidator(andValidator);
    }

    private void setQueenValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_RIGHT),new PathValidator(Direction.UP_RIGHT),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_LEFT),new PathValidator(Direction.DOWN_LEFT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_LEFT),new PathValidator(Direction.UP_LEFT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_RIGHT),new PathValidator(Direction.DOWN_RIGHT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP),
                        new PathValidator(Direction.UP),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN),new PathValidator(Direction.DOWN)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.RIGHT),new PathValidator(Direction.RIGHT)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.LEFT),new PathValidator(Direction.LEFT),
                        new CaptureValidator(true))));
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
                List.of(new UnidirectionalMovementValidator(Direction.UP_RIGHT),new IncrementValidator(1),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_RIGHT),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_LEFT),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_LEFT),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP),new IncrementValidator(1),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.LEFT),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.RIGHT),new IncrementValidator(1),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN),new IncrementValidator(1),new CaptureValidator(true))
        ));
        addDefaultAndValidator(andValidator);
    }


    private void setArchBishopValidator(List<MovementValidator> orValidator, List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_RIGHT),new PathValidator(Direction.UP_RIGHT),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_LEFT),new PathValidator(Direction.DOWN_LEFT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP_LEFT),new PathValidator(Direction.UP_LEFT),new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN_RIGHT),new PathValidator(Direction.DOWN_RIGHT),new CaptureValidator(true))
        ));
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


    private void setChancellorValidator(List<MovementValidator> orValidator,List<MovementValidator> andValidator){
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.UP),
                        new PathValidator(Direction.UP),new CaptureValidator(true)))
        );
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.DOWN),new PathValidator(Direction.DOWN)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.RIGHT),new PathValidator(Direction.RIGHT)
                        ,new CaptureValidator(true))
        ));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(Direction.LEFT),new PathValidator(Direction.LEFT),
                        new CaptureValidator(true))));
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


    private static void addDefaultAndValidator(List<MovementValidator> andValidator){
        andValidator.add(new InBoardValidator());
        andValidator.add(new CheckValidator());
    }

    private List<Direction> getPawnDirections(Color color){
        List<Direction> directions=new ArrayList<>();
        if (color==Color.WHITE){
            directions.add(Direction.UP);
            directions.add(Direction.UP_LEFT);
            directions.add(Direction.UP_RIGHT);
        }else{
            directions.add(Direction.DOWN);
            directions.add(Direction.DOWN_LEFT);
            directions.add(Direction.DOWN_RIGHT);
        }
        return directions;
    }
}
