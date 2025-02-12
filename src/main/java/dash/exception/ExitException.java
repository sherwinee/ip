package dash.exception;

/**
 * An exception to be thrown when the exit command is presented, so that the bot can
 * break from the loop.
 */
public class ExitException extends RuntimeException {
    public ExitException() {
        super("Exit/bye command received.");
    }
}
