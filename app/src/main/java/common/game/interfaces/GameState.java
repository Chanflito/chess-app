package common.game.interfaces;

import common.board.interfaces.Board;
import common.enums.Color;
import common.game.Player;

import java.util.List;

public interface GameState {
    Board getBoard();

    List<Player> getPlayers();

    Color currentTurn();

    GameState copy();
}
