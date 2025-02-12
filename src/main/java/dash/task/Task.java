package dash.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts a LocalDate to a string in dd MMM yyyy format.
     * @param localDate The LocalDate object ot be converted
     * @return The string representation of the LocalDate
     */
    protected static String dateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("d MMM y"));
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns X if the task is done, and a whitespace otherwise.
     * @return X if the task is done, and a whitespace otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the task into string representation to be stored to the Tasks file.
     * @return the string representation of the task
     */
    abstract public String stringify();

    /**
     * Returns true if the task description contains the given string pattern.
     * @param searchStr The string pattern to search for in taskList
     * @return True if the description contains the given string
     */
    public boolean contains(String searchStr) {
        return this.description.contains(searchStr);
    }
}
