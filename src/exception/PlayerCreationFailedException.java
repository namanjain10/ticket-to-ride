package exception;

public class PlayerCreationFailedException extends RuntimeException {

    public PlayerCreationFailedException(String message) {
        super(message);
    }
}
