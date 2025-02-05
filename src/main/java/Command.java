import java.util.ArrayList;

public interface Command {
    public void execute(TaskList taskList, Ui ui);
}
