package dash.command;

import dash.task.TaskList;
import dash.Ui;

/**
 * The command to delete ALL tasks from the TaskList.
 */
public class DeleteAllCommand implements Command {
    public static final String DELETE_ALL_SUCC_MSG = "Ok! I delete all your tasks already.";
    private final String msg;

    public DeleteAllCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        taskList.clear();
        ui.addLine(DELETE_ALL_SUCC_MSG);
        ui.print();
    }

    public String toString() {
        return msg;
    }
}
