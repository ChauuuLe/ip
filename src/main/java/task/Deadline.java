package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the chatbot.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    // Define the custom date-time format
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs a Deadline task with the given description and due date-time.
     *
     * @param description The task description.
     * @param by          The due date-time in the format d/M/yyyy HHmm.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy() + ")";
    }

    /**
     * Formats the due date-time to a more readable format (e.g., Dec 2 2019, 6:00 PM).
     *
     * @return The formatted due date-time.
     */
    protected String formatBy() {
        return by.format(OUTPUT_FORMAT);
    }

    /**
     * Converts the Deadline task to a formatted string for saving to a file.
     *
     * @return The Deadline task in a save-friendly format.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMAT);
    }
}
