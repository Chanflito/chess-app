package clientserver;

import java.net.SocketAddress;

public interface ClientBuilder {
    ClientBuilder withAddress(SocketAddress address);

    ClientBuilder withConnectionListener(ClientConnectionListener listener);

    <T> T addMessageListener(MessageListener<T> listener);

    Client build();
}
