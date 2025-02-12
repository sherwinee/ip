package dash;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a UI and used for formatting and outputting messages from the bot,
 */
public class Ui {

    private final ArrayList<String> botMsgList = new ArrayList<>();
    private final Scanner scan;

    /**
     * Creates a new instance of the Ui class.
     */
    Ui() {
        this.scan = new Scanner(System.in);
    }

    /**
     * Returns the next input from stdin.
     * @return the next input from stdin
     * @throws NoSuchElementException if the stdin is empty.
     */
    public String getNextMsg() throws NoSuchElementException {
        return scan.nextLine();
    }

    /**
     * Adds a line to the output buffer of this Ui class in preparation of printing.
     * @param msg The line to be added
     */
    public void addLine(String msg) {
        botMsgList.add(msg);
    }

    /**
     * Formats and prints the lines currently in the output buffer, then flushes the output buffer.
     */
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

    /**
     * Clears the output buffer.
     */
    public void clear() {
        botMsgList.clear();
    }

    /**
     * Prints the unknown command message.
     */
    public void printDefaultMessage() {
        this.addLine("Alamak! I dont know what that means :/");
        this.print();
    }
}
