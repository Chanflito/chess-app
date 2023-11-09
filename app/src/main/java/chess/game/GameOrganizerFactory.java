package chess.game;

import chess.board.ClassicBoardBuilder;
import chess.board.CustomBoardBuilder;
import common.board.interfaces.BoardBuilder;
import common.enums.Color;
import common.game.ClassicGameState;
import common.game.Player;
import common.game.interfaces.GameOrganizer;
import common.validator.game.AndGameRule;
import common.validator.game.EmptySquareRule;
import common.validator.game.TurnRule;
import common.validator.interfaces.GameRule;

import java.util.List;

public class GameOrganizerFactory {

    public static GameOrganizer createClassicGame(){
        BoardBuilder boardBuilder=new ClassicBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGameState(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build(),Color.WHITE), new ChessGameMover(),
                new ClassicTurnStrategy(), new CheckMate(),normalRules());}

    public static GameOrganizer createCustomGame(){
        BoardBuilder boardBuilder=new CustomBoardBuilder();
        return new ClassicGameOrganizer(new ClassicGameState(List.of(new Player(Color.WHITE), new Player(Color.BLACK)),
                boardBuilder.boardSize(8,8).build(),Color.WHITE), new ChessGameMover(),
                new ClassicTurnStrategy(), new CheckMate(),normalRules());}


    private static GameRule normalRules(){
        return new AndGameRule(List.of(new EmptySquareRule(), new TurnRule()));
    }
}



