public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + "(from: " + from + " to: " + to + ")";
    }
}
