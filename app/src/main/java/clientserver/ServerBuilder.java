package clientserver;

public interface ServerBuilder {
    ServerBuilder withPort(int port);

    ServerBuilder withConnectionListener(ServerConnectionListener listener);

     <T> ServerBuilder addMessageListener(MessageListener<T> listener);

     Server build();
}
