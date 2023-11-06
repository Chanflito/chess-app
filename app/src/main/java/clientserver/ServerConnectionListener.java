package clientserver;

public interface ServerConnectionListener {

    void handleClientConnection(String clientId);

    void handleClientDisconnection(String clientId);
}
