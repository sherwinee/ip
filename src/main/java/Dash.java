import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class Dash {
    public static final String botName = "Dash";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static ArrayList<String> botMsgList = new ArrayList<>();

    public static void botAddLine(String msg) {
        botMsgList.add(msg);
    }

    public static void botPrint() {
        String line = "    ____________________________________________________________";
        String indent = "     ";
        System.out.println(line);
        botMsgList.stream()
                .map(msg -> indent + msg)
                .forEach(newMsg -> System.out.println(newMsg));
        System.out.println(line);
        botMsgList.clear();
    }

    public static void printDefaultMessage() {
        botAddLine("Alamak! I dont know what that means :/");
        botPrint();
    }

    public static void markTask(String msg) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(5));
            Task task = taskList.get(taskNumber - 1);
            task.markDone();
            botAddLine("Ok! I mark this task as done liao!");
            botAddLine("  " + task);
            botPrint();
        } catch (NumberFormatException e) {
            botAddLine("Invalid number format!");
            botPrint();
        } catch (IndexOutOfBoundsException e) {
            botAddLine("That task doesn't exist!");
            botPrint();
        }
    }

    public static void unmarkTask(String msg) {
        try {
            int taskNumber = Integer.parseInt(msg.substring(7));
            Task task = taskList.get(taskNumber - 1);
            task.markUndone();
            botAddLine("Ok! I mark this task as not done yet already!");
            botAddLine("  " + task);
            botPrint();
        } catch (NumberFormatException e) {
            botAddLine("Invalid number format!");
            botPrint();
        } catch (IndexOutOfBoundsException e) {
            botAddLine("That task doesn't exist!");
            botPrint();
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
            botAddLine("Ok! I delete this task already:");
            botAddLine("  " + task.toString());
            botAddLine("Now your list got " + taskList.size() + " tasks.");
            botPrint();
        } catch (IllegalArgumentException e) {
            botAddLine("Aiya! Your task name cannot be empty!");
            botPrint();
        } catch (IndexOutOfBoundsException e) {
            botAddLine("That task doesn't exist!");
            botPrint();
        }
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
            Task task = new Todo(msg);
            taskList.add(task);
            botAddLine("Ok! I add this task already:");
            botAddLine("  " + task.toString());
            botAddLine("Now your list got " + taskList.size() + " tasks.");
            botPrint();
        } catch (IllegalArgumentException e) {
            botAddLine("Your todo description cannot be empty!");
            botPrint();
        }
    }

    public static void addDeadline(String msg) {
        try {
            if (msg.length() < 10 || !msg.contains("/by ")) {
                throw new IllegalArgumentException();
            }
            String desc = msg.substring(9, msg.indexOf("/by ")).strip();
            String by = msg.substring(msg.indexOf("/by ") + 4).strip();
            if (desc.isEmpty() || by.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Task task = new Deadline(desc, by);
            taskList.add(task);
            botAddLine("Ok! I add this task already:");
            botAddLine("  " + task.toString());
            botAddLine("Now your list got " + taskList.size() + " tasks.");
            botPrint();
        } catch (IllegalArgumentException e) {
            botAddLine("Your deadline must have a description and /by time!");
            botPrint();
        }
    }

    public static void addEvent(String msg) {
        try {
            if (msg.length() < 6 || !msg.contains("/from ") || !msg.contains("/to ")) {
                throw new IllegalArgumentException();
            }
            String desc = msg.substring(6, msg.indexOf("/from ")).strip();
            String from = msg.substring(msg.indexOf("/from ") + 6, msg.indexOf("/to ")).strip();
            String to = msg.substring(msg.indexOf("/to ") + 4).strip();
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Task task = new Event(desc, from, to);
            taskList.add(task);
            botAddLine("Ok! I add this task already:");
            botAddLine("  " + task.toString());
            botAddLine("Now your list got " + taskList.size() + " tasks.");
            botPrint();
        } catch (IllegalArgumentException e) {
            botAddLine("Your event must have a description and /from and /to time!");
            botPrint();
        }
    }

    public static void listTasks() {
        if (taskList.isEmpty()) {
            botAddLine("Your list got nothing leh...");
        } else {
            IntStream.range(0, taskList.size())
                    .forEach(i -> botAddLine((i + 1) + ". " + taskList.get(i)));
        }
        botPrint();
    }

    public static void main(String[] args) {
        botAddLine("Hello! I'm " + botName);
        botAddLine("What you want me do today ah?");
        botPrint();

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println();
            String msg;
            try {
                msg = scan.nextLine();
            } catch (NoSuchElementException e) {
                break;
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

        botAddLine("Bye bye! See you ah!");
        botPrint();
    }
}
