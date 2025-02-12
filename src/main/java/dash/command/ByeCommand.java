package dash.command;

import dash.exception.ExitException;
import dash.task.TaskList;
import dash.Ui;

public class ByeCommand implements Command {
    private final String msg;

    public ByeCommand(String msg) {
        this.msg = msg;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        throw new ExitException();
    }

    public String toString() {
        return msg;
    }
}
