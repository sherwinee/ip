package dash;

import dash.command.*;
import dash.exception.ExitException;
import dash.exception.UnknownCommandException;

/**
 * Represents a parser object which contains methods to parse user inputs
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding Command object.
     * @param msg The raw input message from the user
     * @return Command object
     * @throws UnknownCommandException if the command in the message is unrecognised
     * @throws IllegalArgumentException if the input string contains banned characters.
     * @throws ExitException when the command to exit the bot is entered,
     */
    public Command parse(String msg) {
        if (Utils.hasBannedChars(msg)) {
            throw new IllegalArgumentException();
        }

        String alias = msg.split("\\s+")[0];
        CommandEnum commandEnum = CommandEnum.fromString(alias);
        if (commandEnum == CommandEnum.BYE) {
            throw new ExitException();
        }

        switch (commandEnum) {
        case LIST:
            return new ListCommand(msg);

        case MARK:
            return new MarkCommand(msg);

        case UNMARK:
            return new UnmarkCommand(msg);

        case DELETE:
            return new DeleteCommand(msg);

        case DELETEALL:
            return new DeleteAllCommand(msg);

        case TODO:
            return new TodoCommand(msg);

        case DEADLINE:
            return new DeadlineCommand(msg);

        case EVENT:
            return new EventCommand(msg);

        default:
            throw new UnknownCommandException();
        }
    }
}
