package common.game;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.interfaces.GameState;

import java.util.List;

public class ClassicGameState implements GameState {
    private final List<Player> players;

    private final Board currentBoard;

    private final Color color;

    public ClassicGameState(List<Player> players, Board currentBoard, Color color) {
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
    public GameState copy() {
        return new ClassicGameState(this.players,currentBoard.copy(),color);
    }
}
