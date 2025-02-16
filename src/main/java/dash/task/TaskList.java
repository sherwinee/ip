package dash.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A wrapper around an ArrayList storing Task objects.
 * Will have more method next time.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task to the TaskList
     * @param task task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task at the given index from the TaskList
     * @param index index of task to be deleted
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Empties TaskList
     */
    public void clear() {
        this.taskList.clear();
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public Stream<Task> stream() {
        return this.taskList.stream();
    }

    public List<Integer> getIndicesOfTasksFromSearch(String searchStr) {
        return IntStream.range(0, taskList.size())
                .filter(i -> taskList.get(i).contains(searchStr))
                .boxed()
                .toList();
    }
}
