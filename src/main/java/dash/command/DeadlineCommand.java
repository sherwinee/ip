package dash.command;

import dash.*;
import dash.task.Deadline;
import dash.task.Task;
import dash.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The command to add a new Deadline Task.
 */
public class DeadlineCommand implements Command {
    public static final String DATE_PARSE_INVALID_MSG = "Give me the date in yyyy-mm-dd format.";
    public static final String ADD_SUCC_MSG = "Ok! I add this task already:";
    public static final String INVALID_ARGS_MSG = "Your deadline must have a description and /by time!";
    private final String msg;

    public DeadlineCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 10 || !msg.contains("/by ")) {
                throw new IllegalArgumentException();
            }
            String desc = getDesc();
            String byString = getByString();
            if (desc.isEmpty() || byString.isEmpty()) {
                throw new IllegalArgumentException();
            }
            LocalDate byDate;
            try {
                byDate = Utils.parseDate(byString);
            } catch (DateTimeParseException e) {
                ui.addLine(DATE_PARSE_INVALID_MSG);
                ui.print();
                return;
            }
            Task task = new Deadline(desc, byDate);
            taskList.add(task);
            ui.addLine(ADD_SUCC_MSG);
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine(INVALID_ARGS_MSG);
            ui.print();
        }
    }

    private String getByString() {
        return msg.substring(msg.indexOf("/by ") + 4).strip();
    }

    private String getDesc() {
        return msg.substring(9, msg.indexOf("/by ")).strip();
    }

    public String toString() {
        return msg;
    }
}
