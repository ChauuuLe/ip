package dak.command;

import dak.task.TaskList;
import dak.ui.Ui;
import dak.storage.Storage;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
