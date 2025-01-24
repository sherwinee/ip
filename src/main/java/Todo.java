public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return (super.isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.description;
    }
}
