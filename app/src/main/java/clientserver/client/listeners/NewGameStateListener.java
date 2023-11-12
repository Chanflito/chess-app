package clientserver.client.listeners;

import clientserver.client.ClientController;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class NewGameStateListener implements MessageListener<NewGameState> {
    private final ClientController clientController;

    public NewGameStateListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMessage(@NotNull Message<NewGameState> result) {
        clientController.manageMoveResult(result.getPayload());
    }
}
