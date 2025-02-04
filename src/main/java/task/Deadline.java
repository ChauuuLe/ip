package task;

/**
 * Represents a Deadline task in the chatbot.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with the given description and due date/time.
     *
     * @param description The task description.
     * @param by          The due date/time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the Deadline task to a formatted string for saving to a file.
     *
     * @return The Deadline task in a save-friendly format.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
