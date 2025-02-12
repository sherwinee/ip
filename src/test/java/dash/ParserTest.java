package dash;

import dash.command.DeadlineCommand;
import dash.command.TodoCommand;
import dash.exception.UnknownCommandException;
import dash.task.Deadline;
import dash.task.Event;
import dash.task.TaskList;
import dash.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_hasBannedChar_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> new Parser().parse("abc|def"));
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> new Parser().parse("skjandsanod"));
    }

    @Test
    public void parse_todoCommandValid_success() {
        assertEquals(new TodoCommand("todo Read Book").toString(),
                new Parser().parse("todo Read Book").toString());
    }

    @Test
    public void parse_deadlineCommandValid_success() {
        assertEquals(new DeadlineCommand("deadline Submit Work /by 2025-02-23").toString(),
                new Parser().parse("deadline Submit Work /by 2025-02-23").toString());
    }

    @Test
    public void parse_EventCommandValid_success() {
        assertEquals(new DeadlineCommand("event CNY /from 2025-01-29 /to 2025-01-30").toString(),
                new Parser().parse("event CNY /from 2025-01-29 /to 2025-01-30").toString());
    }
}
