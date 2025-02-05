public class Parser {

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
            return new ListCommand();

        case MARK:
            return new MarkCommand(msg);

        case UNMARK:
            return new UnmarkCommand(msg);

        case DELETE:
            return new DeleteCommand(msg);

        case DELETEALL:
            return new DeleteAllCommand();

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
