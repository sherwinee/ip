import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Dash {
    public static final String botName = "Dash";
    private static ArrayList<String> msgList = new ArrayList<>();
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

    public static void addMsg(String msg) {
        msgList.add(msg);
        botAddLine("added: " + msg);
        botPrint();
    }

    public static void printList() {
        IntStream.range(0, msgList.size())
                .forEach(i -> botAddLine((i + 1) + ". " + msgList.get(i)));
        botPrint();
    }

    public static void main(String[] args) {
        botAddLine("Hello! I'm " + botName);
        botAddLine("What you want me do today?");
        botPrint();

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println();
            String msg = scan.next();
            if (msg.equals("bye")) {
                break;
            }
            else if (msg.equals("list")) {
                printList();
            }
            else {
                addMsg(msg);
            }
        }

        botAddLine("Bye bye! See you ah!");
        botPrint();
    }
}
