package dak.task;

/**
 * Represents a task in the chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if a keyword is contained in the description.
     * 
     * @param keyword The keyword that need to be checked.
     * @return True if the description contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Converts the task to a formatted string for saving to a file.
     *
     * @return The task in a save-friendly format.
     */
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
