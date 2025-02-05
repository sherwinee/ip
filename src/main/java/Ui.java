import java.util.ArrayList;

public class Ui {

    private static final ArrayList<String> botMsgList = new ArrayList<>();

    public static void addLine(String msg) {
        botMsgList.add(msg);
    }

    public static void print() {
        String line = "    ____________________________________________________________";
        String indent = "     ";
        System.out.println(line);
        botMsgList.stream()
                .map(msg -> indent + msg)
                .forEach(System.out::println);
        System.out.println(line);
        botMsgList.clear();
    }

    public static void clear() {
        botMsgList.clear();
    }
}
