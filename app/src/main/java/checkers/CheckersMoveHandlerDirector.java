package checkers;


import checkers.mover.CaptureMover;
import checkers.validator.CanCaptureValidator;
import checkers.validator.PreviousCaptureValidator;
import chess.piece.ClassicMoveHandler;
import chess.piece.mover.CompositeMover;
import chess.piece.mover.DefaultMover;
import chess.piece.mover.PromoteMover;
import chess.validator.*;
import common.enums.Direction;
import common.game.Position;
import common.move.MoveHandler;
import common.enums.Color;
import common.validator.interfaces.MovementValidator;
import common.validator.interfaces.PieceMover;

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
        CaptureMover firstCaptureMover=createFirstCaptureMover(color);
        PieceMover promoteMover=createPromotion(color);
        return new ClassicMoveHandler(new CompositeMover(List.of(promoteMover,
                continuousCaptureMover,firstCaptureMover,defaultMover)));
    }

    private void setPawnsValidator(Color color, List<MovementValidator> orValidator,
                                   List<MovementValidator> andValidator) {
        List<Direction> directions=getPawnDirections(color);
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(0)),new PathValidator(directions.get(0)),
                        new CaptureValidator(false),new IncrementValidator(1))));
        orValidator.add(new CompositeAndValidator(
                List.of(new UnidirectionalMovementValidator(directions.get(1)),new PathValidator(directions.get(1)),
                        new CaptureValidator(false),new IncrementValidator(1))));
        addDefaultAndValidator(andValidator);
    }

    private CaptureMover createContinuousCaptureMover(){
        List<Position> directions=getAllDirectionsToCapture();
        return new CaptureMover(List.of(new CompositeAndValidator(List.of(new CanCaptureValidator(directions),
                new PreviousCaptureValidator()))));
    }

    private CaptureMover createFirstCaptureMover(Color color){
        List<Position> directions=getPawnsDirectionToCapture(color);
        return new CaptureMover((List.of(new CanCaptureValidator(directions))));
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

    private static void addDefaultAndValidator(List<MovementValidator> andValidator){
        andValidator.add(new InBoardValidator());
    }

    private PromoteMover createPromotion(Color color){
        CompositeAndValidator promotionRules=new CompositeAndValidator((List.of(new CaptureValidator(false),
                new UnidirectionalMovementValidator(getPawnDirections(color).get(0)),
                new IncrementValidator(1))));
        CompositeNorValidator defaultRules=new CompositeNorValidator((List.of(new InBoardValidator())));
        return new PromoteMover(new CompositeValidator(promotionRules,defaultRules));
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
}
