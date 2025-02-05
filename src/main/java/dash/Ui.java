package dash;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    private final ArrayList<String> botMsgList = new ArrayList<>();
    private final Scanner scan;

    Ui() {
        this.scan = new Scanner(System.in);
    }

    public String getNextMsg() throws NoSuchElementException {
        return scan.nextLine();
    }

    public void addLine(String msg) {
        botMsgList.add(msg);
    }

    public void print() {
        String line = "    ____________________________________________________________";
        String indent = "     ";
        System.out.println(line);
        botMsgList.stream()
                .map(msg -> indent + msg)
                .forEach(System.out::println);
        System.out.println(line + "\n");
        botMsgList.clear();
    }

    public void clear() {
        botMsgList.clear();
    }

    public void printDefaultMessage() {
        this.addLine("Alamak! I dont know what that means :/");
        this.print();
    }
}
