package dak.task;

/**
 * Represents a Todo task in the chatbot.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo task to a formatted string for saving to a file.
     *
     * @return The Todo task in a save-friendly format.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
