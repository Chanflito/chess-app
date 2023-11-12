package clientserver.server.listeners;

import clientserver.server.ServerController;
import edu.austral.ingsis.clientserver.ServerConnectionListener;
import org.jetbrains.annotations.NotNull;

public class ConnectionListener implements ServerConnectionListener {
    private final ServerController serverController;

    public ConnectionListener(ServerController serverController) {
        this.serverController = serverController;
    }

    @Override
    public void handleClientConnection(@NotNull String clientId) {
        serverController.addInitialStateToClient(clientId);
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String clientId) {
        serverController.stopConnection();
    }
}
