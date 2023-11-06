package clientserver;

public interface Server {
    void start();

    void stop();

    <T> T sendMessage(T message,String clientId);

    <T> T broadCast(T message);
 }
