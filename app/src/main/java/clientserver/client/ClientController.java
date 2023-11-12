package clientserver.client;

import clientserver.client.listeners.*;
import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.ClientBuilder;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder;

import java.net.InetSocketAddress;

public class ClientController {
    private final Client client;

    private final GameView gameView;

    public ClientController(GameView gameView) {
        this.gameView = gameView;
        addListenersToGameView();
        client = initClient();
        initConnection();
    }

    public void sendMove(Move move){
        client.send(new Message<>("move",move));
    }

    public void manageInitialState(InitialState initialState){
        gameView.handleInitialState(initialState);
    }

    public void manageMoveResult(MoveResult moveResult){
        gameView.handleMoveResult(moveResult);
    }

    private void addListenersToGameView(){
        gameView.addListener(new ClientGameEventListener(this));
    }

    public void initConnection(){
        client.connect();
    }

    public void closeConnection(){
        client.closeConnection();
    }
    private Client initClient(){
        ClientBuilder clientBuilder= NettyClientBuilder.Companion.createDefault();
        return clientBuilder
                .withAddress(new InetSocketAddress("localhost", 8080))
                .withConnectionListener(new ConnectionListener(this))
                .addMessageListener("initialState", new TypeReference<Message<InitialState>>() {},new InitialStateListener(this))
                .addMessageListener("newGameState", new TypeReference<Message<NewGameState>>() {},new NewGameStateListener(this))
                .addMessageListener("gameOver", new TypeReference<Message<GameOver>>() {},new GameOverListener(this))
                .addMessageListener("invalidMove", new TypeReference<Message<InvalidMove>>() {},new InvalidMoveListener(this))
                .build();
     }
}
