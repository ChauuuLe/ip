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
     * Adds a task to the task list.
     *
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = parseTask(input);
        tasks.addTask(task);
        ui.printMessage("Got it. I've added this task:\n  " + task + "\nNow you have " 
                        + tasks.getTasks().size() + " tasks in the list.");
        saveTask(tasks, storage);
    }

    /**
     * Parses user input and creates the corresponding Task object.
     *
     * @param input The user input string.
     * @return The created Task object.
     * @throws DukeException If the input format is invalid.
     */
    private Task parseTask(String input) throws DukeException {
        if (input.startsWith("todo ")) {
            return createTodo(input);
        } else if (input.startsWith("deadline ")) {
            return createDeadline(input);
        } else if (input.startsWith("event ")) {
            return createEvent(input);
        } else {
            throw new DukeException("Unknown task type.");
        }
    }

    /**
     * Creates a Todo task from the input.
     *
     * @param input The user input string.
     * @return The created Todo task.
     * @throws DukeException If the description is empty.
     */
    private Task createTodo(String input) throws DukeException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Creates a Deadline task from the input.
     *
     * @param input The user input string.
     * @return The created Deadline task.
     * @throws DukeException If the input format is incorrect.
     */
    private Task createDeadline(String input) throws DukeException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new DukeException("Invalid format. Use: deadline <description> /by <date-time>");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("Both the description and deadline date-time must be provided.");
        }
        return new Deadline(description, by); // No date parsing, store as plain string
    }

    /**
     * Creates an Event task from the input.
     *
     * @param input The user input string.
     * @return The created Event task.
     * @throws DukeException If the input format is incorrect.
     */
    private Task createEvent(String input) throws DukeException {
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length < 2) {
            throw new DukeException("Invalid format. Use: event <description> /from <start-time> /to <end-time>");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new DukeException("Invalid format. Use: event <description> /from <start-time> /to <end-time>");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("All event details must be provided.");
        }
        return new Event(description, from, to); // Store as plain strings
    }

    /**
     * Saves the task list to storage.
     *
     * @param tasks The task list.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during saving.
     */
    private void saveTask(TaskList tasks, Storage storage) throws DukeException {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Failed to save task: " + e.getMessage());
        }
    }
}
