package clientserver.client.listeners;

import clientserver.client.ClientController;
import edu.austral.dissis.chess.gui.InvalidMove;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class InvalidMoveListener implements MessageListener<InvalidMove> {
    private final ClientController clientController;

    public InvalidMoveListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMessage(@NotNull Message<InvalidMove> message) {
        clientController.manageMoveResult(message.getPayload());
    }
}
