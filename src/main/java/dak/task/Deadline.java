package dak.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import dak.exceptions.DukeException;

/**
 * Represents a Deadline task in the chatbot.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    // Define the custom date-time format (e.g., "2/12/2019 1800")
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs a Deadline task with the given description and due date-time.
     *
     * @param description The task description.
     * @param by          The due date-time in the format "d/M/yyyy HHmm",
     *                    or in ISO format ("yyyy-MM-dd") for midnight.
     * @throws DukeException If the date-time cannot be parsed.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            // Try parsing using the expected custom format.
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                // Fallback: parse as ISO date (yyyy-MM-dd) and set time to midnight.
                LocalDate date = LocalDate.parse(by);
                this.by = LocalDateTime.of(date, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException e2) {
                throw new DukeException("Invalid date-time format for deadline. Please use 'd/M/yyyy HHmm' (e.g., 2/12/2019 1800) or ISO date 'yyyy-MM-dd' for midnight. Caused by: " 
                        + e2.getMessage());
            }
        }
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
     * Returns the due date-time of the Deadline task.
     *
     * @return The due date-time as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Formats the due date-time to a more readable format (e.g., "Dec 2 2019, 6:00 PM").
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
