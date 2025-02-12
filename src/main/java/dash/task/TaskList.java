package dash.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

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
