package command;

import task.TaskList;
import storage.Storage;
import ui.Ui;

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
