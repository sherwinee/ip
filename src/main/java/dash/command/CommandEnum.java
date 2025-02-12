package dash.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An enumeration of valid commands containing strings that users enter to invoke the commands.
 */
public enum CommandEnum {
    UNKNOWN(),
    BYE("bye"),
    LIST("list"),
    SEARCH("search"),
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

    /**
     * Returns the CommandEnum corresponding to the input command string, or UNKOWN if the string is not recognised.
     * @param alias The string entered by the user representing the command.
     * @return The corresponding CommandEnum.
     */
    public static CommandEnum fromString(String alias) {
        return commandMap.getOrDefault(alias, CommandEnum.UNKNOWN);
    }
}
