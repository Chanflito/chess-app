package chess.director;

import chess.piece.PieceCreator;
import chess.piece.PieceType;
import chess.piece.interfaces.PieceBuilder;
import chess.piece.mover.CompositeMover;
import chess.piece.mover.DefaultMover;
import common.enums.Color;
import common.enums.Direction;
import chess.piece.ClassicMoveHandler;
import common.move.MoveHandler;
import chess.piece.mover.PromoteMover;
import chess.validator.*;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;

import java.util.ArrayList;
import java.util.List;

public class MoveHandlerDirector {
    public MoveHandler createPawnMovement(Color color){
        int direction= color==Color.WHITE?1:-1;
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setPawnsValidator(direction,orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        PieceMover promoteMover=createPromotion(direction);
        return new ClassicMoveHandler(new CompositeMover(List.of(promoteMover,defaultMover)));
    }


    public MoveHandler createRookMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setRooksValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }
    public MoveHandler createBishopMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setBishopValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }

    public MoveHandler createQueenMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setQueenValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }

    public MoveHandler createKnightMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setKnightValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }

    public MoveHandler createKingMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setKingValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }

    public MoveHandler createArchBishopMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setArchBishopValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }

    public MoveHandler createChancellorMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> norValidators=new ArrayList<>();
        CompositeNorValidator andValidator=new CompositeNorValidator(norValidators);
        setChancellorValidator(orValidators,norValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,andValidator);
        return new ClassicMoveHandler(new CompositeMover(List.of(defaultMover)));
    }
    private void setPawnsValidator(int direction,
                                          List<MovementValidator> orValidator, List<MovementValidator> andValidator) {
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

    private PromoteMover createPromotion(int direction){
        CompositeAndValidator promotionRules=new CompositeAndValidator((List.of(new CaptureValidator(false),
                new UnidirectionalMovementValidator(getPawnDirections(direction==1?Color.WHITE:Color.BLACK).get(0)),
                new IncrementValidator(1))));
        CompositeNorValidator defaultRules=new CompositeNorValidator((List.of(new InBoardValidator(),new CheckValidator())));
        PieceBuilder prePiece=new PieceCreator().type(PieceType.QUEEN).movement(createQueenMovement());
        return new PromoteMover(new CompositeValidator(promotionRules,defaultRules),prePiece);
    }
}
