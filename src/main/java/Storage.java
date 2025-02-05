import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Storage {

    private String filePath;
    private TaskList taskList;

    Storage(String filePath, TaskList taskList) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    private String getTaskListString() {
        return this.taskList.stream()
                .map(task -> task.stringify() + "\n")
                .reduce("", (x, y) -> x + y)
                .strip();
    }

    private static Task getTaskFromString(String str) throws IllegalArgumentException, DateTimeParseException {
        String type = str.substring(0, 1);
        String sep = " \\| ";
        List<String> fields = Arrays.asList(str.split(sep));
        fields.forEach(f -> {
            if (f.isEmpty() || Utils.hasBannedChars(f)) {
                throw new IllegalArgumentException();
            }
        });
        switch (type) {
        case "T":
            if (fields.size() != 3) {
                throw new IllegalArgumentException();
            }
            return new Todo(fields.get(2), fields.get(1).equals("1"));

        case "D":
            if (fields.size() != 4) {
                throw new IllegalArgumentException();
            }
            return new Deadline(fields.get(2), fields.get(1).equals("1"), Utils.parseDate(fields.get(3)));

        case "E":
            if (fields.size() != 5) {
                throw new IllegalArgumentException();
            }

            return new Event(fields.get(2), fields.get(1).equals("1"), Utils.parseDate(fields.get(3)), Utils.parseDate(fields.get(4)));

        }

        throw new IllegalArgumentException();
    }

    public void loadTasks() throws FileNotFoundException, IllegalArgumentException, DateTimeParseException {
        File taskFile = new File(filePath);
        Scanner scan = new Scanner(taskFile);
        Stream.generate(() -> scan.hasNextLine() ? scan.nextLine() : "")
                .takeWhile(s -> !s.isEmpty())
                .map(Storage::getTaskFromString)
                .forEachOrdered(taskList::add);
    }

    public void saveTasks() throws IOException {
        // Create the directory if it doesn't exist
        Files.createDirectories(Paths.get(filePath).getParent());

        FileWriter fw = new FileWriter(filePath, false);
        fw.write(getTaskListString());
        fw.close();
    }
}
