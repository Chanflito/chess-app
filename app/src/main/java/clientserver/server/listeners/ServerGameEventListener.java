package clientserver.server.listeners;

import clientserver.server.ServerController;
import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import org.jetbrains.annotations.NotNull;

public class ServerGameEventListener implements GameEventListener {
    private final ServerController serverController;

    public ServerGameEventListener(ServerController serverController) {
        this.serverController = serverController;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        serverController.notifyMove(move);
    }
}
