package game;

import board.ClassicBoardBuilder;
import board.CustomBoardBuilder;
import board.interfaces.BoardBuilder;
import enums.Color;
import game.interfaces.GameOrganizer;

import java.util.List;

public class GameOrganizerBuilder {

    public static GameOrganizer createClassicGame(){
        BoardBuilder boardBuilder=new ClassicBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGame(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build()), new ClassicGameMover(),
                new ClassicTurnHandler(Color.WHITE), new ClassicWinCondition());}

    public static GameOrganizer createCustomGame(){
        BoardBuilder boardBuilder=new CustomBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGame(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build()), new ClassicGameMover(),
                new ClassicTurnHandler(Color.WHITE), new ClassicWinCondition());}
    }


