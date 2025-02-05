package dash;

import dash.command.Command;
import dash.exception.ExitException;
import dash.exception.UnknownCommandException;
import dash.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.lang.IllegalArgumentException;

public class Dash {
    public static final String botName = "dash.Dash";

    private static final String filePath = "./data/dash.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    Dash(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(Dash.filePath, taskList);
        this.parser = new Parser();

        try {
            storage.loadTasks();
            ui.addLine("Tasks loaded from file " + filePath);
            ui.print();
        } catch (FileNotFoundException e) {
            ui.addLine("No tasks file detected. Starting new session.");
            ui.print();
        } catch (IllegalArgumentException e) {
            ui.addLine("The tasks file at " + filePath + " is corrupted. Starting new session.");
            ui.print();
        } catch (DateTimeParseException e) {
            ui.addLine("The tasks file at " + filePath + " contains invalid date formats. Starting new session.");
            ui.print();
        }
    }

    public void run() {
        ui.addLine("Hello! I'm " + botName);
        ui.addLine("What you want me do today ah?");
        ui.print();


        while (true) {
            String msg;
            try {
                msg = ui.getNextMsg();
            } catch (NoSuchElementException e) {
                break;
            }

            // Parsing
            Command command;
            try {
                command = parser.parse(msg);
            } catch (IllegalArgumentException e) {
                ui.addLine("The following characters are not allowed:");
                ui.addLine(Utils.BANNED_CHARS.stream()
                        .reduce("", (x, y) -> x + y));
                ui.print();
                continue;
            } catch (UnknownCommandException e) {
                ui.printDefaultMessage();
                continue;
            } catch (ExitException e) {
                break;
            }

            // dash.command.Command Execution
            try {
                command.execute(taskList, ui);
            } catch (Exception e) {
                ui.addLine("An unknown error occurred.");
                ui.addLine("Details:");
                ui.addLine(e.toString());
                ui.print();
            }
        }

        ui.addLine("Bye bye! See you ah!");
        ui.print();
        try {
            storage.saveTasks();
            ui.addLine("Tasks saved to " + filePath);
            ui.print();
        } catch (IOException e) {
            ui.addLine("Cannot write to file at " + filePath);
            ui.addLine("");
            ui.addLine("Details:");
            ui.addLine(e.toString());
            ui.print();
        }
    }

    public static void main(String[] args) {
        new Dash("./data/dash.Dash.txt").run();
    }
}
