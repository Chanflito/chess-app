package chess.game;

import chess.board.ClassicBoardBuilder;
import chess.board.CustomBoardBuilder;
import common.board.interfaces.BoardBuilder;
import common.enums.Color;
import common.game.ClassicGameState;
import common.game.Player;
import common.game.interfaces.GameOrganizer;

import java.util.List;

public class GameOrganizerFactory {

    public static GameOrganizer createClassicGame(){
        BoardBuilder boardBuilder=new ClassicBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGameState(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build(),Color.WHITE), new ClassicGameMover(),
                new ClassicTurnStrategy(), new ClassicWinCondition());}

    public static GameOrganizer createCustomGame(){
        BoardBuilder boardBuilder=new CustomBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGameState(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build(),Color.WHITE), new ClassicGameMover(),
                new ClassicTurnStrategy(), new ClassicWinCondition());}
    }

