package dash.command;

import dash.task.TaskList;
import dash.Ui;

public interface Command {
    public void execute(TaskList taskList, Ui ui);
}
