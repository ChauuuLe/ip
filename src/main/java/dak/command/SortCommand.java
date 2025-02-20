package dak.command;

import dak.task.TaskList;
import dak.ui.Ui;
import dak.storage.Storage;

/**
 * Sorts the tasks in the task list by deadline chronologically.
 */
public class SortCommand extends Command {

    /**
     * Executes the sorting of tasks based on deadlines.
     * 
     * @param tasks The task list to be sorted.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTasksByDeadline();
        ui.printMessage("Tasks have been sorted by deadline");
        tasks.listTasks(ui);
    }
}
