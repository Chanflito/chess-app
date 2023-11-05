package checkers.director;


import checkers.mover.CaptureMover;
import checkers.validator.CanCaptureValidator;
import common.mover.CaptureAndPromoteMover;
import common.validator.validators.PieceCountValidator;
import checkers.validator.PreviousCaptureValidator;
import common.piece.ClassicMoveHandler;
import common.piece.PieceCreator;
import common.enums.PieceType;
import common.piece.PieceBuilder;
import common.mover.CompositeOrMover;
import common.mover.DefaultMover;
import common.mover.PromoteMover;
import common.enums.Direction;
import common.game.Position;
import common.move.MoveHandler;
import common.enums.Color;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;
import common.validator.validators.*;

import java.util.ArrayList;
import java.util.List;

public class CheckersMoveHandlerDirector {

    public MoveHandler createPawnMovement(Color color){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> andValidators=new ArrayList<>();
        CompositeNorValidator norValidator=new CompositeNorValidator(andValidators);
        setPawnsValidator(color,orValidators,andValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,norValidator);
        CaptureMover continuousCaptureMover=createContinuousCaptureMover();
        CaptureMover firstCaptureMover=createFirstCaptureMover(getPawnsDirectionToCapture(color));
        PieceMover promoteMover=createPromotion(color);
        PieceMover captureWithContinuousAndPromoteMover=new CaptureAndPromoteMover(List.of(continuousCaptureMover,promoteMover),buildPreQueen(color));
        PieceMover firstCaptureAndPromoteMover=new CaptureAndPromoteMover(List.of(firstCaptureMover,promoteMover),buildPreQueen(color));
        return new ClassicMoveHandler(new CompositeOrMover(List.of(firstCaptureAndPromoteMover,captureWithContinuousAndPromoteMover,promoteMover,
                continuousCaptureMover,firstCaptureMover,defaultMover)));
    }

    public MoveHandler createQueenMovement(){
        List<MovementValidator> orValidators=new ArrayList<>();
        CompositeOrValidator orValidator=new CompositeOrValidator(orValidators);
        List<MovementValidator> andValidators=new ArrayList<>();
        CompositeNorValidator norValidator=new CompositeNorValidator(andValidators);
        setQueenValidator(orValidators,andValidators);
        DefaultMover defaultMover= new DefaultMover(orValidator,norValidator);
        CaptureMover continuousCaptureMover=createContinuousCaptureMover();
        CaptureMover firstCaptureMover=createFirstCaptureMover(getAllDirectionsToCapture());
        return new ClassicMoveHandler(new CompositeOrMover(List.of(continuousCaptureMover,firstCaptureMover,defaultMover)));
    }
    private void setPawnsValidator(Color color, List<MovementValidator> orValidator,
                                   List<MovementValidator> andValidator) {
        List<Direction> directions=getPawnDirections(color);
        orValidator.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(0)),new PathValidator(directions.get(0)),
                        new CaptureValidator(false),new IncrementValidator(1)))));
        orValidator.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(1)),new PathValidator(directions.get(1)),
                        new CaptureValidator(false),new IncrementValidator(1)))));
        addDefaultAndValidator(andValidator);
    }

    private void setQueenValidator(List<MovementValidator> orValidator,
                                   List<MovementValidator> andValidator) {
        List<Direction> directions=getQueenDirections();
        orValidator.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(0)),new PathValidator(directions.get(0)),
                        new CaptureValidator(false),new IncrementValidator(1)))));
        orValidator.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(1)),new PathValidator(directions.get(1)),
                        new CaptureValidator(false),new IncrementValidator(1)))));
        orValidator.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(2)),new PathValidator(directions.get(2)),
                        new CaptureValidator(false),new IncrementValidator(1)))));
        orValidator.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(3)),new PathValidator(directions.get(3)),
                        new CaptureValidator(false),new IncrementValidator(1)))));
        addDefaultAndValidator(andValidator);
    }
    private CaptureMover createContinuousCaptureMover(){
        List<Position> directions=getAllDirectionsToCapture();
        return new CaptureMover(List.of(new CompositeAndValidator(List.of(new CanCaptureValidator(directions),
                new PreviousCaptureValidator()))));
    }

    private CaptureMover createFirstCaptureMover( List<Position> directions){
        return new CaptureMover(List.of(new CompositeXorValidator(new PreviousCaptureValidator(),new CanCaptureValidator(directions))));
    }
    private List<Direction> getPawnDirections(Color color){
        List<Direction> directions=new ArrayList<>();
        if (color==Color.WHITE){
            directions.add(Direction.UP_LEFT);
            directions.add(Direction.UP_RIGHT);
        }else{
            directions.add(Direction.DOWN_LEFT);
            directions.add(Direction.DOWN_RIGHT);
        }
        return directions;
    }

    private List<Direction> getQueenDirections(){
        return List.of(Direction.UP_LEFT,Direction.UP_RIGHT,Direction.DOWN_LEFT,Direction.DOWN_RIGHT);
    }
    private static void addDefaultAndValidator(List<MovementValidator> andValidator){
        andValidator.add(new InBoardValidator());
    }

    private PromoteMover createPromotion(Color color){
        PieceBuilder pieceBuilder= buildPreQueen(color);
        MovementValidator promotionRules=createPromotionRules(color);
        CompositeNorValidator defaultRules=new CompositeNorValidator((List.of(new InBoardValidator())));
        return new PromoteMover(new CompositeValidator(promotionRules,defaultRules),pieceBuilder);
    }

    private PieceBuilder buildPreQueen(Color color) {
        return new PieceCreator().type(PieceType.QUEEN).movement(createQueenMovement()).color(color);
    }

    private List<Position> getPawnsDirectionToCapture(Color color){
        List<Position> positions=new ArrayList<>();
        if (color==Color.WHITE){
            positions.add(new Position(2,2));
            positions.add(new Position(-2,2));
        }else{
            positions.add(new Position(2,-2));
            positions.add(new Position(-2,-2));
        }
        return positions;
    }

    private static List<Position> getAllDirectionsToCapture(){
        return List.of(new Position(2,2),new Position(2,-2),new Position(-2,2),new Position(-2,-2));

    }

//    private MovementValidator createPromotionRules(Color color){
//        List<MovementValidator> rules=new ArrayList<>();
//        List<Direction> directions=getPawnDirections(color);
//        rules.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
//                List.of(new UnidirectionalMovementValidator(directions.get(0)),new PathValidator(directions.get(0)),
//                        new CaptureValidator(false),new IncrementValidator(1)))));
//        rules.add(new CompositeXorValidator(new PieceCountValidator(),new CompositeAndValidator(
//                List.of(new UnidirectionalMovementValidator(directions.get(1)),new PathValidator(directions.get(1)),
//                        new CaptureValidator(false),new IncrementValidator(1)))));
//        rules.add((new CompositeAndValidator(List.of(new CanCaptureValidator(getAllDirectionsToCapture()),
//                new PreviousCaptureValidator()))));
//        rules.add(new CompositeXorValidator(new PreviousCaptureValidator(),new CanCaptureValidator(getPawnsDirectionToCapture(color))));
//        return new CompositeAndValidator(List.of((new CompositeOrValidator(rules))));
//    }

    private MovementValidator createPromotionRules(Color color){
        List<MovementValidator> rules=new ArrayList<>();
        List<Direction> directions=getPawnDirections(color);
        rules.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(0)),new PathValidator(directions.get(0)),
                        new CaptureValidator(false),new IncrementValidator(1))));
        rules.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(1)),new PathValidator(directions.get(1)),
                        new CaptureValidator(false),new IncrementValidator(1))));
        rules.add((new CompositeAndValidator(List.of(new CanCaptureValidator(getAllDirectionsToCapture()),
                new PreviousCaptureValidator()))));
        rules.add(new CompositeXorValidator(new PreviousCaptureValidator(),new CanCaptureValidator(getPawnsDirectionToCapture(color))));
        return new CompositeAndValidator(List.of((new CompositeOrValidator(rules))));
    }
}

