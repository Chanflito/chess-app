package chess.game;

import common.board.interfaces.Board;
import common.game.interfaces.Game;

import java.util.List;

public class ClassicGame implements Game {
    private final List<Player> players;

    private final Board currentBoard;


    public ClassicGame(List<Player> players, Board currentBoard) {
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
    public Game copy() {
        return new ClassicGame(this.players,currentBoard.copy());
    }
}
