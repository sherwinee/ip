package dash;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Contains properties and attributes used by other classes.
 */
public class Utils {

    static final List<String> BANNED_CHARS = List.of("|");

    /**
     * Checks for banned characters in the given string.
     * @param msg The string to be checked for banned characters.
     * @return True if the given string contains banned characters, false otherwise.
     */
    public static boolean hasBannedChars(String msg) {
        return Utils.BANNED_CHARS.stream().anyMatch(msg::contains);
    }

    /**
     * Parses the given string to a DateTime object.
     * @param dateString
     * @return the corresponding DateTime object.
     * @throws DateTimeParseException if the given string is invalid.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        // Will contain more logic in the future
        return LocalDate.parse(dateString);
    }
}
