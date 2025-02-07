package dak.command;

import dak.task.Task;
import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.exceptions.DukeException;
import java.io.IOException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the task index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Delete task from the task list.
     *
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 1 || taskIndex > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }

        Task removedTask = tasks.getTasks().remove(taskIndex - 1);
        ui.printMessage("Noted. I've removed this task:\n  " + removedTask);

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks: " + e.getMessage());
        }
    }
}
