package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.Ui;

/**
 * The command to delete a specific task.
 */
public class DeleteCommand implements Command {
    public static final String TASK_EMPTY_MSG = "Aiya! Your task name cannot be empty!";
    public static final String TASK_NO_EXIST_MSG = "That task doesn't exist!";
    public static final String DELETE_SUCC_MSG = "Ok! I delete this task already:";
    private final String msg;

    public DeleteCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 8) {
                throw new IllegalArgumentException();
            }
            int taskNumber = getTaskNumber();
            Task task = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            ui.addLine(DELETE_SUCC_MSG);
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine(TASK_EMPTY_MSG);
            ui.print();
        } catch (IndexOutOfBoundsException e) {
            ui.addLine(TASK_NO_EXIST_MSG);
            ui.print();
        }
    }

    private int getTaskNumber() {
        return Integer.parseInt(msg.substring(7));
    }

    public String toString() {
        return msg;
    }
}
