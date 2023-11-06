package common.game.interfaces;

import common.board.interfaces.Board;
import common.game.Player;

import java.util.List;

public interface GameData {
    Board getBoard();

    List<Player> getPlayers();

    GameData copy();
}
