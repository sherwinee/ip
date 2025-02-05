package dash.command;

import dash.task.TaskList;
import dash.Ui;

public class DeleteAllCommand implements Command {
    public void execute(TaskList taskList, Ui ui) {
        taskList.clear();
        ui.addLine("Ok! I delete all your tasks already.");
        ui.print();
    }
}
