package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.task.Todo;
import dash.Ui;

/**
 * The command to add a new Todo Task.
 */
public class TodoCommand implements Command {
    public static final String ADD_SUCC_MSG = "Ok! I add this task already:";
    public static final String EMPTY_DESC_MSG = "Your todo description cannot be empty!";
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
            Task task = new Todo(desc);
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

    private String getDesc() {
        return msg.substring(5).strip();
    }

    public String toString() {
        return msg;
    }
}
