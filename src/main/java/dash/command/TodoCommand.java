package dash.command;

import dash.task.Task;
import dash.task.TaskList;
import dash.task.Todo;
import dash.Ui;

public class TodoCommand implements Command {
    private final String msg;

    public TodoCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 6) {
                throw new IllegalArgumentException();
            }
            String desc = msg.substring(5).strip();
            if (desc.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Task task = new Todo(desc);
            taskList.add(task);
            ui.addLine("Ok! I add this task already:");
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine("Your todo description cannot be empty!");
            ui.print();
        }
    }
}
