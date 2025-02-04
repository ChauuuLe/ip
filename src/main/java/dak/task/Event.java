package dak.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the chatbot.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    // Define the custom date-time format
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The task description.
     * @param from        The start time in the format d/M/yyyy HHmm.
     * @param to          The end time in the format d/M/yyyy HHmm.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatFrom() + " to: " + formatTo() + ")";
    }

    /**
     * Formats the start time to a more readable format.
     *
     * @return The formatted start time.
     */
    protected String formatFrom() {
        return from.format(OUTPUT_FORMAT);
    }

    /**
     * Formats the end time to a more readable format.
     *
     * @return The formatted end time.
     */
    protected String formatTo() {
        return to.format(OUTPUT_FORMAT);
    }

    /**
     * Converts the Event task to a formatted string for saving to a file.
     *
     * @return The Event task in a save-friendly format.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }
}
