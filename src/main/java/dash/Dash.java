package dash;

import dash.command.Command;
import dash.exception.ExitException;
import dash.exception.UnknownCommandException;
import dash.task.TaskList;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

/**
 * Represents the bot object.
 */
public class Dash {
    public static final String botName = "dash.Dash";

    private final String filePath;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    Dash(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(this.filePath, taskList);
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

    /**
     * Starts the bot instance and continues receiving input until
     * the command to exit is entered.
     */
    public String getResponse(String msg) {
        // Parsing
        Command command;
        try {
            command = parser.parse(msg);
            command.execute(taskList, ui);
            storage.saveTasks();
        } catch (IllegalArgumentException e) {
            ui.addLine("The following characters are not allowed:");
            ui.addLine(Utils.BANNED_CHARS.stream()
                    .reduce("", (x, y) -> x + y));
        } catch (UnknownCommandException e) {
            ui.setDefaultMessage();
        } catch (ExitException e) {
            ui.setExitMessage();
            // Ends the programme in 2 seconds without blocking
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2-seconds delay
            delay.setOnFinished(event -> Platform.exit()); // Exit JavaFX after delay
            delay.play();
        } catch (IOException e) {
            ui.appendSaveFailureMessage();
        }
        return ui.getResponse();
    }
}
