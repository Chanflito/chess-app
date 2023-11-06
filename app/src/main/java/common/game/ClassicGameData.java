package common.game;

import common.board.interfaces.Board;
import common.game.interfaces.GameData;

import java.util.List;

public class ClassicGameData implements GameData {
    private final List<Player> players;

    private final Board currentBoard;


    public ClassicGameData(List<Player> players, Board currentBoard) {
        this.players = players;
        this.currentBoard = currentBoard;
    }

    @Override
    public Board getBoard() {
        return this.currentBoard;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }


    @Override
    public GameData copy() {
        return new ClassicGameData(this.players,currentBoard.copy());
    }
}
