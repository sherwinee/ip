public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        throw new ExitException();
    }
}
