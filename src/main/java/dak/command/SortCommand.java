package dak.command;

import dak.task.TaskList;
import dak.ui.Ui;
import dak.storage.Storage;
import dak.task.Deadline;
import java.util.ArrayList;

/**
 * Sorts the tasks in the task list by deadline chronologically.
 */
public class SortCommand extends Command {

    /**
     * Executes the sorting of tasks based on deadlines and displays only deadline tasks.
     * 
     * @param tasks   The task list to be sorted.
     * @param ui      The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Deadline> sortedDeadlines = tasks.getSortedDeadlines();
        if (sortedDeadlines.isEmpty()) {
            ui.printMessage("There are no deadline tasks in your list.");
        } else {
            StringBuilder listMessage = new StringBuilder("Here are the deadlines in your list:");
            for (int i = 0; i < sortedDeadlines.size(); i++) {
                listMessage.append("\n  ").append(i + 1).append(". ").append(sortedDeadlines.get(i));
            }
            ui.printMessage(listMessage.toString());
        }
    }
}
