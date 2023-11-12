package clientserver.server;

import clientserver.server.listeners.ConnectionListener;
import clientserver.server.listeners.ServerGameEventListener;
import clientserver.server.listeners.ServerEventListener;
import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerBuilder;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;

public class ServerController {
    private final Server server;
    private final GameEngine gameEngine;


    public ServerController(GameEngine gameEngine) {
        this.gameEngine= gameEngine;
        server=initServer();
        server.start();

    }

    //Cuando hay un movimiento en mi partida debo hacer un broadcast.

    public void notifyMove(Move move){
        MoveResult result=gameEngine.applyMove(move);
        broadCastResult(result);
    }

    public InitialState initialState(){
        return gameEngine.init();
    }

    public void addInitialStateToClient(String clientId){
        Message<InitialState> message=new Message<>("initialState",initialState());
        server.sendMessage(clientId,message);
    }
    public void stopConnection(){
        server.stop();
    }

    private void broadCastResult(MoveResult result){
        if (result instanceof NewGameState){
            Message<MoveResult> message= new Message<>("newGameState",result);
            server.broadcast(message);
        }
        else if (result instanceof GameOver){
            Message<MoveResult> message=new Message<>("gameOver",result);
            server.broadcast(message);
        }
        else if (result instanceof InvalidMove){
            Message<MoveResult> message=new Message<>("invalidMove",result);
            server.broadcast(message);
        }
    }

    private Server initServer(){
        ServerBuilder serverBuilder=NettyServerBuilder.Companion.createDefault();
        GameEventListener gameEventListener=new ServerGameEventListener(this);
        return serverBuilder
                .withPort(8080)
                .withConnectionListener(new ConnectionListener(this))
                .addMessageListener("move", new TypeReference<Message<Move>>() {},new ServerEventListener(gameEventListener))
                .build();
    }

}
