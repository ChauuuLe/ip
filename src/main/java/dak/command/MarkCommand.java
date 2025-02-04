package dak.command;

import dak.task.TaskList;
import dak.task.Task;
import dak.ui.Ui;
import dak.storage.Storage;
import dak.exceptions.DukeException;
import java.io.IOException;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand.
     *
     * @param taskIndex The index of the task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 1 || taskIndex > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }

        Task task = tasks.getTasks().get(taskIndex - 1);
        task.markAsDone();
        ui.printMessage("Nice! I've marked this task as done:\n  " + task);

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks: " + e.getMessage());
        }
    }
}
