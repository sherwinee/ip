package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.Ui;

/**
 * The command to mark a specific Task as done.
 */
public class MarkCommand implements Command {
    private final String msg;

    public MarkCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(5));
            Task task = taskList.get(taskNumber - 1);
            task.markDone();
            ui.addLine("Ok! I mark this task as done liao!");
            ui.addLine("  " + task);
            ui.print();
        } catch (NumberFormatException e) {
            ui.addLine("Invalid number format!");
            ui.print();
        } catch (IndexOutOfBoundsException e) {
            ui.addLine("That task doesn't exist!");
            ui.print();
        }
    }

    public String toString() {
        return msg;
    }
}
