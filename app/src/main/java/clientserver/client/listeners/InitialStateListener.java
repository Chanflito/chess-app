package clientserver.client.listeners;

import clientserver.client.ClientController;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class InitialStateListener implements MessageListener<InitialState>{
    private final ClientController clientController;

    public InitialStateListener(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void handleMessage(@NotNull Message<InitialState> result) {
        clientController.manageInitialState(result.getPayload());
    }
}
