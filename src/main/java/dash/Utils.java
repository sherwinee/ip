package dash;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Utils {

    static final List<String> BANNED_CHARS = List.of("|");

    public static boolean hasBannedChars(String msg) {
        return Utils.BANNED_CHARS.stream().anyMatch(msg::contains);
    }

    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        // Will contain more logic in the future
        return LocalDate.parse(dateString);
    }
}
