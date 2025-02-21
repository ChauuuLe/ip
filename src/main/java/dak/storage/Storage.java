package dak.storage;

import dak.task.Task;
import dak.task.Todo;
import dak.task.Deadline;
import dak.task.Event;
import dak.exceptions.DukeException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles storage for loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        assert filePath != null : "Storage file path should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file. Creates an empty list if the file does not exist.
     * 
     * @return The list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     * @throws DukeException If an error occurs while parsing a task.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Check if the file exists; if not, return an empty list
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create parent directory if it doesn't exist
            file.createNewFile();          // Create the file
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     * 
     * @param tasks The list of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     * 
     * @param line The line to parse.
     * @return The corresponding Task object.
     * @throws DukeException If the task type is invalid or the line is malformed.
     */
    private Task parseTask(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                Task todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                Task deadline = new Deadline(description, parts[3]);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                Task event = new Event(description, parts[3], parts[4]);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                throw new DukeException("Invalid task type in file: " + taskType);
        }
    }
}
