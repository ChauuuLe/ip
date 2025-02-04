package task;

import java.util.ArrayList;
import ui.Ui;

/**
 * Manages the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with pre-loaded tasks.
     *
     * @param tasks The pre-loaded tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Lists all tasks.
     *
     * @param ui The Ui object to handle printing.
     */
    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.printMessage("You have no tasks in your list.");
        } else {
            StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                listMessage.append("\n  ").append(i + 1).append(". ").append(tasks.get(i));
            }
            ui.printMessage(listMessage.toString());
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
