package common.game;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.interfaces.GameData;

import java.util.List;

public class ClassicGameData implements GameData {
    private final List<Player> players;

    private final Board currentBoard;

    private final Color color;

    public ClassicGameData(List<Player> players, Board currentBoard, Color color) {
        this.players = players;
        this.currentBoard = currentBoard;
        this.color = color;
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
    public Color currentTurn() {
        return this.color;
    }


    @Override
    public GameData copy() {
        return new ClassicGameData(this.players,currentBoard.copy(),color);
    }
}
