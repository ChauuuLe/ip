package dak.command;

import dak.task.TaskList;
import dak.task.Task;
import dak.ui.Ui;
import dak.storage.Storage;
import java.util.ArrayList;

/**
 * Find command with given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs an FindCommand with the keyword that user input.
     *
     * @param keyword The user input string.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Listing all commands that contain the given keyword.
     * 
     * @param tasks The task list.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasksWithKeyword(this.keyword);
        
        if (foundTasks.isEmpty() == false) {
            StringBuilder listMessage = new StringBuilder("Here are the matching tasks in your list:");

            for (int i = 0; i < foundTasks.size(); i++) {
                listMessage.append("\n  ").append(i + 1).append(". ").append(foundTasks.get(i));
            }
            ui.printMessage(listMessage.toString());
        } else {
            ui.printMessage("There is no matched command to your keyword.");
        }
    }
}
