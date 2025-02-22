package dash.command;

import dash.*;
import dash.task.Event;
import dash.task.Task;
import dash.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The command to add a new Event Task.
 */
public class EventCommand implements Command {
    public static final String INVALID_DATE_FORMAT_MSG = "Give me the dates in yyyy-mm-dd format.";
    public static final String INVALID_ARGS_MSG =
            "Usage: event <description> /from <date> /to <date> [#tags (separated by spaces)]";
    private final String msg;

    public EventCommand(String msg) { this.msg = msg; }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 6 || !msg.contains("/from ") || !msg.contains("/to ")) {
                throw new IllegalArgumentException();
            }
            String desc = getDesc();
            String fromString = getFromString();
            String toString = getToString();
            if (desc.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
                throw new IllegalArgumentException();
            }
            LocalDate fromDate;
            LocalDate toDate;
            try {
                fromDate = Utils.parseDate(fromString);
                toDate = Utils.parseDate(toString);
            } catch (DateTimeParseException e) {
                ui.addLine(INVALID_DATE_FORMAT_MSG);
                ui.print();
                return;
            }
            List<String> tags = getTags();
            Task task = new Event(desc, tags, fromDate, toDate);
            taskList.add(task);
            ui.addLine("Ok! I add this task already:");
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine(INVALID_ARGS_MSG);
            ui.print();
        }
    }

    private List<String> getTags() throws IllegalArgumentException {
        if (!msg.contains("#")) {
            return List.<String>of();
        }
        List<String> allTags =  Arrays.asList(msg.substring(msg.indexOf("#")).split(" "));
        // Check for tags not starting with #
        if (allTags.stream().anyMatch(tag -> tag.charAt(0) != '#')) {
            throw new IllegalArgumentException();
        }
        if (allTags.stream().anyMatch(tag -> tag.substring(1).contains("#") || tag.length() < 2)) {
            throw new IllegalArgumentException();
        }

        return allTags.stream().map(tag -> tag.substring(1)).toList();
    }

    private String getToString() {
        if (msg.contains("#")) {
            return msg.substring(msg.indexOf("/to ") + 4, msg.indexOf("#")).strip();
        }
        return msg.substring(msg.indexOf("/to ") + 4).strip();
    }

    private String getFromString() {
        return msg.substring(msg.indexOf("/from ") + 6, msg.indexOf("/to ")).strip();
    }

    private String getDesc() {
        return msg.substring(6, msg.indexOf("/from ")).strip();
    }

    public String toString() {
        return msg;
    }
}
