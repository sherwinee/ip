package dash.command;

import dash.task.TaskList;
import dash.Ui;

/**
 * Represents the command the user entered, with a method to execute the command actions.
 */
public interface Command {
    /**
     * Executes the command on the given TaskList and Ui instances.
     * @param taskList the TaskList to execute the command on
     * @param ui the Ui to execute the command on
     */
    public void execute(TaskList taskList, Ui ui);
}
