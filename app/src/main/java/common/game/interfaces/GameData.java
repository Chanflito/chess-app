package common.game.interfaces;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Player;

import java.util.List;

public interface GameData {
    Board getBoard();

    List<Player> getPlayers();

    Color currentTurn();

    GameData copy();
}
