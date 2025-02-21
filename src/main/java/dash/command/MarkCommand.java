package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.Ui;

/**
 * The command to mark a specific Task as done.
 */
public class MarkCommand implements Command {
    public static final String INVALID_NUMBER_FORMAT_MSG = "Invalid number format!";
    public static final String NONEXISTENT_TASK_MSG = "That task doesn't exist!";
    public static final String MARK_SUCC_MSG = "Ok! I mark this task as done liao!";
    private final String msg;

    public MarkCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(5));
            Task task = taskList.get(taskNumber - 1);
            task.markDone();
            ui.addLine(MARK_SUCC_MSG);
            ui.addLine("  " + task);
            ui.print();
        } catch (NumberFormatException e) {
            ui.addLine(INVALID_NUMBER_FORMAT_MSG);
            ui.print();
        } catch (IndexOutOfBoundsException e) {
            ui.addLine(NONEXISTENT_TASK_MSG);
            ui.print();
        }
    }

    public String toString() {
        return msg;
    }
}
