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
        return;
    }

    /**
     * Flushes the buffer and returns the lines added for output
     * @return the output
     */
    public String getResponse() {
        String response = botMsgList.stream()
                .reduce("", (x, y) -> x + y + "\n")
                .strip();
        this.clear();
        return response;
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
    public void setDefaultMessage() {
        this.clear();
        this.addLine("Alamak! I dont know what that means :/");
    }

    public void setExitMessage() {
        this.clear();
        this.addLine("Bye bye! See you again soon ah!");
    }

    public void appendSaveFailureMessage() {
        this.addLine("");
        this.addLine("(Warning: Failed to save to file)");
    }
}
