package dash.task;

import java.util.List;

public class Todo extends Task {

    public Todo(String description, List<String> tags) {
        super(description, tags);
    }
    public Todo(String description, List<String> tags, boolean isDone) {
        super(description, tags, isDone);
    }

    public String stringify() {
        return String.format("T|%d|%s|%s", super.isDone ? 1 : 0, super.description, super.stringifyTags());
    }

    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description + getTagString();
    }
}
