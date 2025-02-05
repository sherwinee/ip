package dash.command;

import dash.exception.ExitException;
import dash.task.TaskList;
import dash.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        throw new ExitException();
    }
}
