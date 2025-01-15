package exception;

public class ConnectionAlreadyOccupiedException extends RuntimeException {
    public ConnectionAlreadyOccupiedException(String message) {
        super(message);
    }
}
