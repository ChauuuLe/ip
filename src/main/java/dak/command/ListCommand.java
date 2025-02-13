package dak.command;

import dak.task.TaskList;
import dak.ui.Ui;
import dak.storage.Storage;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Show tasks in the task list.
     *
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
