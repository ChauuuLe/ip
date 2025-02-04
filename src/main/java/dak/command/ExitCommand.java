package dak.command;

import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;

/**
 * Exits the chatbot.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
