package dash.command;

import dash.Ui;
import dash.task.TaskList;

import java.util.List;

public class SearchCommand implements Command {
    public static final String INVALID_ARGS_MSG = "Syntax: Search <search string>";
    private final String msg;

    public SearchCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 8) {
                throw new IllegalArgumentException();
            }
            String searchStr = getSearchStr();
            if (searchStr.isEmpty()) {
                throw new IllegalArgumentException();
            }
            List<Integer> indices;
            if (searchStr.charAt(0) == '#') {
                indices = taskList.getIndicesOfTasksByTag(searchStr.substring(1));
            } else {
                indices = taskList.getIndicesOfTasksFromSearch(searchStr);
            }
            if (indices.isEmpty()) {
                ui.addLine("Cannot find anything with " + searchStr + " leh...");
            } else {
                updateMatchingIndices(taskList, ui, indices);
            }
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine(INVALID_ARGS_MSG);
            ui.print();
        }
    }

    private static void updateMatchingIndices(TaskList taskList, Ui ui, List<Integer> indices) {
        indices.forEach(i -> ui.addLine((i + 1) + ". " + taskList.get(i)));
    }

    private String getSearchStr() {
        return msg.substring(7).strip();
    }
}
