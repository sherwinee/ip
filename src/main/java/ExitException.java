public class ExitException extends RuntimeException {
    public ExitException() {
        super("Exit/bye command received.");
    }
}
