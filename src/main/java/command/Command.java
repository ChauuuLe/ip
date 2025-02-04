package command;

import task.TaskList;
import storage.Storage;
import ui.Ui;
import exceptions.DukeException;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @throws DukeException If there is an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether this command will exit the application.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
