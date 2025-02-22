package dash.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a Task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    public Task(String description, List<String> tags) {
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    public Task(String description, List<String> tags, boolean isDone) {
        this.description = description;
        this.tags = tags;
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
     * Concatenates tags and separates them with whitespaces
     * @return String representation of tags
     */
    public String stringifyTags() {
        return tags.stream().reduce("", (x, y) -> x + (x.isEmpty() ? "" : " ") + y);
    }

    /**
     * Returns true if the task description contains the given string pattern.
     * @param searchStr The string pattern to search for in taskList
     * @return True if the description contains the given string
     */
    public boolean nameContains(String searchStr) {
        return this.description.contains(searchStr);
    }

    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }

    public String getTagString() {
        return tags.isEmpty() ? "" : "\n        Tags: " +
                tags.stream()
                        .map(tag -> "#" + tag)
                        .reduce("", (x, y) -> x + (x.isEmpty() ? "" : " ") + y);
    }
}
