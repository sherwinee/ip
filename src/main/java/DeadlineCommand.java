import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private final String msg;

    DeadlineCommand(String msg) {
        this.msg = msg;
    }

    public void execute(TaskList taskList, Ui ui) {
        try {
            if (msg.length() < 10 || !msg.contains("/by ")) {
                throw new IllegalArgumentException();
            }
            String desc = msg.substring(9, msg.indexOf("/by ")).strip();
            String byString = msg.substring(msg.indexOf("/by ") + 4).strip();
            if (desc.isEmpty() || byString.isEmpty()) {
                throw new IllegalArgumentException();
            }
            LocalDate byDate;
            try {
                byDate = Utils.parseDate(byString);
            } catch (DateTimeParseException e) {
                ui.addLine("Give me the date in yyyy-mm-dd format.");
                ui.print();
                return;
            }
            Task task = new Deadline(desc, byDate);
            taskList.add(task);
            ui.addLine("Ok! I add this task already:");
            ui.addLine("  " + task.toString());
            ui.addLine("Now your list got " + taskList.size() + " tasks.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine("Your deadline must have a description and /by time!");
            ui.print();
        }
    }
}
