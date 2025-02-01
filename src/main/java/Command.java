import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Command {
    UNKNOWN(),
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private static final Map<String, Command> commandMap = new HashMap<String, Command>();
    private final Set<String> aliases;

    static {
        for (Command command : Command.values()) {
            for (String alias : command.aliases) {
                commandMap.put(alias, command);
            }
        }
    }

    Command(String... aliases) {
        this.aliases = Set.<String>of(aliases);
    }

    public static Command fromString(String alias) {
        return commandMap.getOrDefault(alias, Command.UNKNOWN);
    }
}
