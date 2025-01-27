package exception;

public class NoTicketLeftInDeck extends RuntimeException {
    public NoTicketLeftInDeck(String message) {
        super(message);
    }
}
