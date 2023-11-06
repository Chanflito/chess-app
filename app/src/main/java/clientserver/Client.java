package clientserver;


public interface Client {
    void connect();

    void disconnect();

    <T> T send(T message);
}
