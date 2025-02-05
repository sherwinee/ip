import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    public String stringify() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0,
                super.description, this.by);
    }

    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " +
                super.description + " (by: " + dateToString(by) + ")";
    }
}
