package dash.command;

import dash.*;
import dash.task.Deadline;
import dash.task.Task;
import dash.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The command to add a new Deadline Task.
 */
public class DeadlineCommand implements Command {
    public static final String DATE_PARSE_INVALID_MSG = "Give me the date in yyyy-mm-dd format.";
    public static final String ADD_SUCC_MSG = "Ok! I add this task already:";
    public static final String INVALID_ARGS_MSG =
            "Usage: deadline <description> /by <date> [#tags (separated by spaces)]";
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
            List<String> tags = getTags();
            Task task = new Deadline(desc, tags, byDate);
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

    /**
     * Parses tags from user input and returns a List of tag Strings
     * @return String List of tags
     * @throws IllegalArgumentException if tags are given in an invalid format
     */
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

    /**
     * Parses the deadline date string from user input
     * @return String representation of "by" date
     */
    private String getByString() {
        if (msg.contains("#")) {
            return msg.substring(msg.indexOf("/by ") + 4, msg.indexOf("#")).strip();
        }
        return msg.substring(msg.indexOf("/by ") + 4).strip();
    }

    /**
     * Parses the description from user input
     * @return Task description
     */
    private String getDesc() {
        return msg.substring(9, msg.indexOf("/by ")).strip();
    }

    public String toString() {
        return msg;
    }
}
