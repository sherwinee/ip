public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException() {
        super("Unknown command received.");
    }
}
