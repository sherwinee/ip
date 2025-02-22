package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.task.Todo;
import dash.Ui;

import java.util.Arrays;
import java.util.List;

/**
 * The command to add a new Todo Task.
 */
public class TodoCommand implements Command {
    public static final String ADD_SUCC_MSG = "Ok! I add this task already:";
    public static final String EMPTY_DESC_MSG = "Your todo description or tag (Optional) cannot be empty!";
    private final String msg;

    public TodoCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 6) {
                throw new IllegalArgumentException();
            }
            String desc = getDesc();
            if (desc.isEmpty()) {
                throw new IllegalArgumentException();
            }
            List<String> tags = getTags();
            Task task = new Todo(desc, tags);
            taskList.add(task);
            ui.addLine(ADD_SUCC_MSG);
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine(EMPTY_DESC_MSG);
            ui.print();
        }
    }

    /**
     * Parses the description from user input
     * @return Task description
     */
    private String getDesc() {
        if (msg.contains("#")) {
            return msg.substring(5, msg.indexOf("#")).strip();
        }
        return msg.substring(5).strip();
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

    public String toString() {
        return msg;
    }
}
