package exception;

public class CurrentPlayerNotFoundException extends RuntimeException {
    public CurrentPlayerNotFoundException(String message) {
        super(message);
    }
}
