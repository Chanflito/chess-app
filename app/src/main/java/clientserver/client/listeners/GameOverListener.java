package clientserver.client.listeners;

import clientserver.client.ClientController;
import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class GameOverListener implements MessageListener<GameOver> {
    private final ClientController clientController;

    public GameOverListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMessage(@NotNull Message<GameOver> result) {
        clientController.manageMoveResult(result.getPayload());
    }
}
