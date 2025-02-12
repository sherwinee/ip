package dash;

import dash.task.Event;
import dash.task.TaskList;
import dash.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void getTaskFromString_correctFormat_success() {
        String testString1 = "T | 0 | Buy groceries";
        assertEquals(new Todo("Buy groceries").toString(), Storage.getTaskFromString(testString1).toString());

        String testString2 = "E | 0 | Career fair | 2025-01-20 | 2025-01-21";
        assertEquals(new Event("Career fair", LocalDate.parse("2025-01-20"), LocalDate.parse("2025-01-21")).toString(),
                Storage.getTaskFromString(testString2).toString());
    }

    @Test
    public void getTaskFromString_invalidFormat_failure() {
        String testString = "E || test todo";
        assertThrows(IllegalArgumentException.class, () -> Storage.getTaskFromString(testString));

        String testString2 = "G | 1 | invalid event";
        assertThrows(IllegalArgumentException.class, () -> Storage.getTaskFromString(testString));
    }

    @Test
    public void getTaskListString_withTasks_success() {
        Event testEvent = new Event("Career fair", LocalDate.parse("2025-01-20"), LocalDate.parse("2025-01-21"));
        Todo testTodo = new Todo("test todo");
        TaskList tasks = new TaskList();
        tasks.add(testEvent);
        Storage storage = new Storage("./data/TestDash.txt", tasks);
        assertEquals("E | 0 | Career fair | 2025-01-20 | 2025-01-21", storage.getTaskListString());
    }
}
