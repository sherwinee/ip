package dash.task;

import java.time.LocalDate;
import java.util.List;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, List<String> tags, LocalDate by) {
        super(description, tags);
        this.by = by;
    }

    public Deadline(String description, List<String> tags, boolean isDone, LocalDate by) {
        super(description, tags, isDone);
        this.by = by;
    }

    public String stringify() {
        return String.format("D|%d|%s|%s|%s", super.isDone ? 1 : 0,
                super.description, this.by, super.stringifyTags());
    }

    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " +
                super.description + " (by: " + dateToString(by) + ")" + getTagString();
    }
}
