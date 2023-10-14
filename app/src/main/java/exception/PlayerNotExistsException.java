package exception;

public class PlayerNotExistsException extends RuntimeException {
    public PlayerNotExistsException(String message) {
        super(message);
    }
}
