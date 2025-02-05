package dash.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String stringify() {
        return String.format("E | %d | %s | %s | %s", super.isDone ? 1 : 0,
                super.description, this.from, this.to);
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description +
                " (from: " + dateToString(from) + " to: " + dateToString(to) + ")";
    }
}
