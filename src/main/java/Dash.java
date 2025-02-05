import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.IntStream;
import java.lang.IllegalArgumentException;

public class Dash {
    public static final String botName = "Dash";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static final String FILE_PATH = "./data/dash.txt";

    public static void printDefaultMessage() {
        Ui.addLine("Alamak! I dont know what that means :/");
        Ui.print();
    }

    public static void markTask(String msg) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(5));
            Task task = taskList.get(taskNumber - 1);
            task.markDone();
            Ui.addLine("Ok! I mark this task as done liao!");
            Ui.addLine("  " + task);
            Ui.print();
        } catch (NumberFormatException e) {
            Ui.addLine("Invalid number format!");
            Ui.print();
        } catch (IndexOutOfBoundsException e) {
            Ui.addLine("That task doesn't exist!");
            Ui.print();
        }
    }

    public static void unmarkTask(String msg) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(7));
            Task task = taskList.get(taskNumber - 1);
            task.markUndone();
            Ui.addLine("Ok! I mark this task as not done yet already!");
            Ui.addLine("  " + task);
            Ui.print();
        } catch (NumberFormatException e) {
            Ui.addLine("Invalid number format!");
            Ui.print();
        } catch (IndexOutOfBoundsException e) {
            Ui.addLine("That task doesn't exist!");
            Ui.print();
        }
    }

    public static void deleteTask(String msg) {
        try {
            if (msg.length() < 8) {
                throw new IllegalArgumentException();
            }
            int taskNumber = Integer.parseInt(msg.substring(7));
            Task task = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            Ui.addLine("Ok! I delete this task already:");
            Ui.addLine("  " + task.toString());
            Ui.addLine("Now your list got " + taskList.size() + " tasks.");
            Ui.print();
        } catch (IllegalArgumentException e) {
            Ui.addLine("Aiya! Your task name cannot be empty!");
            Ui.print();
        } catch (IndexOutOfBoundsException e) {
            Ui.addLine("That task doesn't exist!");
            Ui.print();
        }
    }

    public static void deleteAllTasks() {
        taskList.clear();
        Ui.addLine("Ok! I delete all your tasks already.");
        Ui.print();
    }

    public static void addTodo(String msg) {
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
            Ui.addLine("Ok! I add this task already:");
            Ui.addLine("  " + task.toString());
            Ui.addLine("Now your list got " + taskList.size() + " tasks.");
            Ui.print();
        } catch (IllegalArgumentException e) {
            Ui.addLine("Your todo description cannot be empty!");
            Ui.print();
        }
    }

    public static void addDeadline(String msg) {
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
                Ui.addLine("Give me the date in yyyy-mm-dd format.");
                Ui.print();
                return;
            }
            Task task = new Deadline(desc, byDate);
            taskList.add(task);
            Ui.addLine("Ok! I add this task already:");
            Ui.addLine("  " + task.toString());
            Ui.addLine("Now your list got " + taskList.size() + " tasks.");
            Ui.print();
        } catch (IllegalArgumentException e) {
            Ui.addLine("Your deadline must have a description and /by time!");
            Ui.print();
        }
    }

    public static void addEvent(String msg) {
        try {
            if (msg.length() < 6 || !msg.contains("/from ") || !msg.contains("/to ")) {
                throw new IllegalArgumentException();
            }
            String desc = msg.substring(6, msg.indexOf("/from ")).strip();
            String fromString = msg.substring(msg.indexOf("/from ") + 6, msg.indexOf("/to ")).strip();
            String toString = msg.substring(msg.indexOf("/to ") + 4).strip();
            if (desc.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
                throw new IllegalArgumentException();
            }
            LocalDate fromDate;
            LocalDate toDate;
            try {
                fromDate = Utils.parseDate(fromString);
                toDate = Utils.parseDate(toString);
            } catch (DateTimeParseException e) {
                Ui.addLine("Give me the dates in yyyy-mm-dd format.");
                Ui.print();
                return;
            }
            Task task = new Event(desc, fromDate, toDate);
            taskList.add(task);
            Ui.addLine("Ok! I add this task already:");
            Ui.addLine("  " + task.toString());
            Ui.addLine("Now your list got " + taskList.size() + " tasks.");
            Ui.print();
        } catch (IllegalArgumentException e) {
            Ui.addLine("Your event must have a description and /from and /to time!");
            Ui.print();
        }
    }

    public static void listTasks() {
        if (taskList.isEmpty()) {
            Ui.addLine("Your list got nothing leh...");
        } else {
            IntStream.range(0, taskList.size())
                    .forEach(i -> Ui.addLine((i + 1) + ". " + taskList.get(i)));
        }
        Ui.print();
    }

    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH, taskList);
        storage.loadTasks();
        Ui.addLine("Hello! I'm " + botName);
        Ui.addLine("What you want me do today ah?");
        Ui.print();

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println();
            String msg;
            try {
                msg = scan.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            if (Utils.hasBannedChars(msg)) {
                Ui.addLine("The following characters are not allowed:");
                Ui.addLine(Utils.BANNED_CHARS.stream().reduce("", (x, y) -> x + y));
                Ui.print();
                continue;
            }

            String alias = msg.split("\\s+")[0];
            Command command = Command.fromString(alias);
            if (command == Command.BYE) {
                break;
            }

            switch (command) {
            case LIST:
                listTasks();
                break;

            case MARK:
                markTask(msg);
                break;

            case UNMARK:
                unmarkTask(msg);
                break;

            case DELETE:
                deleteTask(msg);
                break;

            case DELETEALL:
                deleteAllTasks();
                break;

            case TODO:
                addTodo(msg);
                break;

            case DEADLINE:
                addDeadline(msg);
                break;

            case EVENT:
                addEvent(msg);
                break;

            default:
                printDefaultMessage();
                break;
            }
            }

        Ui.addLine("Bye bye! See you ah!");
        Ui.print();
        storage.saveTasks();
    }
}
