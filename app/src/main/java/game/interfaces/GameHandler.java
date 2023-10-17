package game.interfaces;

import result.Result;

public interface GameHandler {
    Result<Boolean,Game> checkMovers();
}
