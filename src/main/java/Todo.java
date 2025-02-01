public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String stringify() {
        return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
    }

    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
