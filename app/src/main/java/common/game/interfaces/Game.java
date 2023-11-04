package common.game.interfaces;

import common.board.interfaces.Board;
import common.game.Player;

import java.util.List;

public interface Game {
    Board getBoard();

    List<Player> getPlayers();

    Game copy();
}
