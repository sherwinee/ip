package dash.exception;

/**
 * An exception that is thrown if the command entered by the user is unrecognised.
 */
public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException() {
        super("Unknown command received.");
    }
}
