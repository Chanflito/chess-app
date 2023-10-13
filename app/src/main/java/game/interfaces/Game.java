package game.interfaces;

import board.interfaces.Board;
import game.Player;

import java.util.List;

public interface Game {
    Board getBoard();

    List<Player> getPlayers();

    Game copy();
}
