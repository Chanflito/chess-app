package clientserver.client.listeners;

import clientserver.client.ClientController;
import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import org.jetbrains.annotations.NotNull;

public class ClientGameEventListener implements GameEventListener {

    private final ClientController clientController;

    public ClientGameEventListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        clientController.sendMove(move);
    }
}
