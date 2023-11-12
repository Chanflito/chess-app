package clientserver.client.listeners;

import clientserver.client.ClientController;
import edu.austral.ingsis.clientserver.ClientConnectionListener;

public class ConnectionListener implements ClientConnectionListener {
    private final ClientController clientController;

    public ConnectionListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleConnection() {
        clientController.initConnection();
    }

    @Override
    public void handleConnectionClosed() {
        clientController.closeConnection();
    }
}
