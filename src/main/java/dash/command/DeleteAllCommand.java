package dash.command;

import dash.task.TaskList;
import dash.Ui;

/**
 * The command to delete ALL tasks from the TaskList.
 */
public class DeleteAllCommand implements Command {
    private final String msg;

    public DeleteAllCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        taskList.clear();
        ui.addLine("Ok! I delete all your tasks already.");
        ui.print();
    }

    public String toString() {
        return msg;
    }
}
