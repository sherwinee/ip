import java.util.stream.IntStream;

public class ListCommand implements Command {
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isEmpty()) {
            ui.addLine("Your list got nothing leh...");
        } else {
            IntStream.range(0, taskList.size())
                    .forEach(i -> ui.addLine((i + 1) + ". " + taskList.get(i)));
        }
        ui.print();
    }
}
