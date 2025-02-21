package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.Ui;

/**
 * The command to mark a specific Task as not done.
 */
public class UnmarkCommand implements Command {
    public static final String UNMARK_SUCC_MSG = "Ok! I mark this task as not done yet already!";
    public static final String INVALID_NUMBER_FORMAT_MSG = "Invalid number format!";
    public static final String NONEXISTENT_TASK_MSG = "That task doesn't exist!";
    private final String msg;

    public UnmarkCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(7));
            Task task = taskList.get(taskNumber - 1);
            task.markUndone();
            ui.addLine(UNMARK_SUCC_MSG);
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
