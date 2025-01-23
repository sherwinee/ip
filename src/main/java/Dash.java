import java.util.Scanner;

public class Dash {
    public static void println() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }
    public static void main(String[] args) {
        String botName = "Dash";
        String line = "____________________________________________________________";
        println();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        println();

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println();
            String msg = scan.next();
            if (msg.equals("bye")) {
                break;
            }
            println();
            System.out.println(msg);
            println();
        }

        println();
        System.out.println("Bye. Hope to see you soon!");
        println();
    }
}
