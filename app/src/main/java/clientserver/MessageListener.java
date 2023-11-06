package clientserver;

public interface MessageListener <T> {
    void handleMessage(T message);
}
