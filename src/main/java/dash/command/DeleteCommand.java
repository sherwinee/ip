package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.Ui;

public class DeleteCommand implements Command {
    private final String msg;

    public DeleteCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 8) {
                throw new IllegalArgumentException();
            }
            int taskNumber = Integer.parseInt(msg.substring(7));
            Task task = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            ui.addLine("Ok! I delete this task already:");
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine("Aiya! Your task name cannot be empty!");
            ui.print();
        } catch (IndexOutOfBoundsException e) {
            ui.addLine("That task doesn't exist!");
            ui.print();
        }
    }
}
