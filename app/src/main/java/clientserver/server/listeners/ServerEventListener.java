package clientserver.server.listeners;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class ServerEventListener implements MessageListener<Move>{
    private final GameEventListener gameEventListener;

    public ServerEventListener(GameEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
    }

    @Override
    public void handleMessage(@NotNull Message<Move>message) {
        gameEventListener.handleMove(message.getPayload());
    }
}
