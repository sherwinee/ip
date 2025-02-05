public class UnmarkCommand implements Command {
    private final String msg;

    UnmarkCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(7));
            Task task = taskList.get(taskNumber - 1);
            task.markUndone();
            ui.addLine("Ok! I mark this task as not done yet already!");
            ui.addLine("  " + task);
            ui.print();
        } catch (NumberFormatException e) {
            ui.addLine("Invalid number format!");
            ui.print();
        } catch (IndexOutOfBoundsException e) {
            ui.addLine("That task doesn't exist!");
            ui.print();
        }
    }
}
