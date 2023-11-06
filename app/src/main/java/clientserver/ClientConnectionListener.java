package clientserver;

public interface ClientConnectionListener {
    void handleConnection();

    void handleDisconnection();
}
