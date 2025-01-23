import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

    public static void addTask(String msg) {
        taskList.add(new Task(msg));
        botAddLine("added: " + msg);
        botPrint();
    }

    public static void printTaskList() {
        IntStream.range(0, taskList.size())
                .forEach(i -> botAddLine((i + 1) + ". " + taskList.get(i)));
        botPrint();
    }

    public static void main(String[] args) {
        botAddLine("Hello! I'm " + botName);
        botAddLine("What you want me do today?");
        botPrint();

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println();
            String msg = scan.nextLine();
            if (msg.equals("bye")) {
                break;
            }
            else if (msg.equals("list")) {
                printTaskList();
            } else if (msg.startsWith("mark ")) {
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
                }
            } else if (msg.startsWith("unmark ")) {
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
                }
            } else {
                addTask(msg);
            }
        }

        botAddLine("Bye bye! See you ah!");
        botPrint();
    }
}
