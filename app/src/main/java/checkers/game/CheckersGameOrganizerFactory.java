package checkers.game;


import checkers.board.CheckersBoardBuilder;
import checkers.helper.TurnHelper;
import chess.game.ClassicGameOrganizer;
import common.wincondition.AllEnemyPiecesCaptureCondition;

import common.game.ClassicGameState;
import common.game.Player;
import common.board.interfaces.BoardBuilder;
import common.enums.Color;
import common.game.interfaces.GameOrganizer;

import java.util.List;

public class CheckersGameOrganizerFactory {
    public static GameOrganizer createCheckersGame(){
        BoardBuilder boardBuilder=new CheckersBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGameState(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build(),Color.WHITE),
                new CheckersGameMover(),
                new CheckersTurnStrategy(new TurnHelper()),
                new AllEnemyPiecesCaptureCondition());}
}

