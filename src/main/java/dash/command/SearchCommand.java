package dash.command;

import dash.Ui;
import dash.task.TaskList;

import java.util.List;

public class SearchCommand implements Command {
    private final String msg;

    public SearchCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 8) {
                throw new IllegalArgumentException();
            }
            String searchStr = msg.substring(7).strip();
            if (searchStr.isEmpty()) {
                throw new IllegalArgumentException();
            }
            List<Integer> indices = taskList.getIndicesOfTasksFromSearch(searchStr);
            if (indices.isEmpty()) {
                ui.addLine("Cannot find anything with " + searchStr + " leh...");
            } else {
                indices.forEach(i -> ui.addLine((i + 1) + ". " + taskList.get(i)));
            }
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine("Syntax: Search <search string>");
            ui.print();
        }
    }
}
