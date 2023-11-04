package checkers;


import checkers.wincondition.AllEnemyPiecesCaptureCondition;
import chess.game.*;
import common.board.interfaces.BoardBuilder;
import common.enums.Color;
import common.game.interfaces.GameOrganizer;

import java.util.List;

public class CheckersGameOrganizerBuilder {

    public static GameOrganizer createCheckersGame(){
        BoardBuilder boardBuilder=new CheckersBoardBuilder();
        return new CheckersGameOrganizer(new ClassicGame(List.of(new Player(Color.WHITE), new Player(Color.BLACK)), boardBuilder.boardSize(8,8).build()),
                new CheckersGameMover(),
                new CheckersTurnHandler(Color.WHITE),
                new AllEnemyPiecesCaptureCondition());}

}
