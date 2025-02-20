package dak.task;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import dak.ui.Ui;

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
    
    /**
     * Finds and returns a list of tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in tasks.
     * @return A list of tasks that contain the given keyword.
     */
    public ArrayList<Task> findTasksWithKeyword(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.containsKeyword(keyword)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }

    /**
     * Sorts tasks chronologically by deadline date.
     * Other tasks remain in their original positions.
     */
    public void sortTasksByDeadline() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1 instanceof Deadline && t2 instanceof Deadline) {
                    return ((Deadline) t1).getBy().compareTo(((Deadline) t2).getBy());
                } else if (t1 instanceof Deadline) {
                    return -1; // Deadline comes first
                } else if (t2 instanceof Deadline) {
                    return 1; // Other tasks stay after Deadline
                }
                return 0; // No change for non-Deadline tasks
            }
        });
    }
}
