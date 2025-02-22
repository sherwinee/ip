package dash.task;

import java.time.LocalDate;
import java.util.List;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, List<String> tags, LocalDate from, LocalDate to) {
        super(description, tags);
        this.from = from;
        this.to = to;
    }

    public Event(String description, List<String> tags, boolean isDone, LocalDate from, LocalDate to) {
        super(description, tags, isDone);
        this.from = from;
        this.to = to;
    }

    public String stringify() {
        return String.format("E|%d|%s|%s|%s|%s", super.isDone ? 1 : 0,
                super.description, this.from, this.to, super.stringifyTags());
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description +
                " (from: " + dateToString(from) + " to: " + dateToString(to) + ")" + getTagString();
    }
}
