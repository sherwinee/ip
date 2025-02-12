package dash;

import dash.task.Deadline;
import dash.task.Event;
import dash.task.Task;
import dash.task.TaskList;
import dash.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * A class used to manage file I/O tasks of the bot
 */
public class Storage {

    private String filePath;
    private TaskList taskList;

    /**
     * Creates an instance of Storage
     * @param filePath Path of the persistence file containing saved tasks
     * @param taskList The reference to the TaskList object to be used
     */
    Storage(String filePath, TaskList taskList) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    /**
     * Returns the formatted string of tasks derived from the TaskList attribute
     * for saving to the saved tasks file. Returns an empty string if the taskList is empty
     * @return The formatted string of tasks
     */
    public String getFormattedTasksString() {
        return this.taskList.stream()
                .map(task -> task.stringify() + "\n")
                .reduce("", (x, y) -> x + y)
                .strip();
    }

    /**
     * Parses each line from the saved tasks file and returns the corresponding
     * Task object.
     * @param str The task string (Single line of the tasks file)
     * @return The Task object parsed from the given string
     * @throws IllegalArgumentException If the format is corrupted or contains banned characters
     * @throws DateTimeParseException If the date string fails to be parsed due to invalid format
     */
    public static Task parseTaskFromString(String str) throws IllegalArgumentException, DateTimeParseException {
        String type = str.substring(0, 1);
        String sep = " \\| ";
        List<String> fields = Arrays.asList(str.split(sep));
        fields.forEach(f -> {
            if (f.isEmpty() || Utils.hasBannedChars(f)) {
                throw new IllegalArgumentException();
            }
        });
        switch (type) {
        case "T":
            if (fields.size() != 3) {
                throw new IllegalArgumentException();
            }
            return new Todo(fields.get(2), fields.get(1).equals("1"));

        case "D":
            if (fields.size() != 4) {
                throw new IllegalArgumentException();
            }
            return new Deadline(fields.get(2), fields.get(1).equals("1"), Utils.parseDate(fields.get(3)));

        case "E":
            if (fields.size() != 5) {
                throw new IllegalArgumentException();
            }

            return new Event(fields.get(2), fields.get(1).equals("1"), Utils.parseDate(fields.get(3)), Utils.parseDate(fields.get(4)));

        }

        throw new IllegalArgumentException();
    }

    /**
     * Reads the file from the path in the <code>filePath</code> attribute and
     * populates the taskList instance with Task objects parsed from the file
     * @throws FileNotFoundException If the given <code>filePath</code> does not exist
     * @throws IllegalArgumentException If the file contains corrupted lines or banned characters
     * @throws DateTimeParseException If one or more date strings are invalid
     */
    public void loadTasks() throws FileNotFoundException, IllegalArgumentException, DateTimeParseException {
        File taskFile = new File(filePath);
        Scanner scan = new Scanner(taskFile);
        Stream.generate(() -> scan.hasNextLine() ? scan.nextLine() : "")
                .takeWhile(s -> !s.isEmpty())
                .map(Storage::parseTaskFromString)
                .forEachOrdered(taskList::add);
    }

    /**
     * Saves the tasks in the <code>taskList</code> attribute to the file in the
     * <code>filePath</code> attribute.
     * @throws IOException If the file cannot be written to.
     */
    public void saveTasks() throws IOException {
        // Create the directory if it doesn't exist
        Files.createDirectories(Paths.get(filePath).getParent());

        FileWriter fw = new FileWriter(filePath, false);
        fw.write(getFormattedTasksString());
        fw.close();
    }
}
