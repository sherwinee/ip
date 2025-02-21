package dash.command;

import dash.task.TaskList;
import dash.Ui;

import java.util.stream.IntStream;

/**
 * The command to list all tasks in the TaskList.
 */
public class ListCommand implements Command {
    public static final String NO_TASKS_MSG = "Your list got nothing leh...";
    private final String msg;

    public ListCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isEmpty()) {
            ui.addLine(NO_TASKS_MSG);
        } else {
            IntStream.range(0, taskList.size())
                    .forEach(i -> ui.addLine((i + 1) + ". " + taskList.get(i)));
        }
        ui.print();
    }

    public String toString() {
        return msg;
    }
}
