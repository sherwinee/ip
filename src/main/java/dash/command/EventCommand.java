package dash.command;

import dash.*;
import dash.task.Event;
import dash.task.Task;
import dash.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The command to add a new Event Task.
 */
public class EventCommand implements Command {
    public static final String INVALID_DATE_FORMAT_MSG = "Give me the dates in yyyy-mm-dd format.";
    public static final String INVALID_ARGS_MSG = "Your event must have a description and /from and /to time!";
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
            Task task = new Event(desc, fromDate, toDate);
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

    private String getToString() {
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
