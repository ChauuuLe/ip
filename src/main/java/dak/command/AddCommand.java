package dak.command;

import dak.task.Task;
import dak.task.Todo;
import dak.task.Deadline;
import dak.task.Event;
import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.exceptions.DukeException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Adds a new task to the task list.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Constructs an AddCommand with the user input.
     *
     * @param input The user input string.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Add task to the task list.
     *
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;

        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(description);

        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new DukeException("Invalid format. Use: deadline <description> /by <d/M/yyyy HHmm>");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new DukeException("Both the description and deadline date-time must be provided.");
            }

            task = new Deadline(description, by);

        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from ");
            if (parts.length < 2) {
                throw new DukeException("Invalid format. Use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new DukeException("Invalid format. Use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new DukeException("All event details must be provided.");
            }

            task = new Event(description, from, to);

        } else {
            throw new DukeException("Unknown task type.");
        }

        tasks.addTask(task);
        ui.printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + tasks.getTasks().size() + " tasks in the list.");

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Failed to save task: " + e.getMessage());
        }
    }
}
