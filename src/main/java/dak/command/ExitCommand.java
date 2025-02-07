package dak.command;

import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;

/**
 * Exits the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Exit from chatbot
     *
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
