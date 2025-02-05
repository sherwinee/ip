import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CommandEnum {
    UNKNOWN(),
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete", "del"),
    DELETEALL("deleteall", "delall"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private static final Map<String, CommandEnum> commandMap = new HashMap<String, CommandEnum>();
    private final Set<String> aliases;

    static {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            for (String alias : commandEnum.aliases) {
                commandMap.put(alias, commandEnum);
            }
        }
    }

    CommandEnum(String... aliases) {
        this.aliases = Set.<String>of(aliases);
    }

    public static CommandEnum fromString(String alias) {
        return commandMap.getOrDefault(alias, CommandEnum.UNKNOWN);
    }
}
