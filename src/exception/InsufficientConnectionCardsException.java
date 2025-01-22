package exception;

public class InsufficientConnectionCardsException extends RuntimeException {
    public InsufficientConnectionCardsException(String message) {
        super(message);
    }
}
