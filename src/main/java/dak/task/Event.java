package dak.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import dak.exceptions.DukeException;

/**
 * Represents an Event task in the chatbot.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    // Define the custom date-time format (e.g., "2/12/2019 1800")
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs an Event task with the given description, start date-time, and end date-time.
     *
     * @param description The task description.
     * @param from        The start date-time in the format "d/M/yyyy HHmm", or ISO date ("yyyy-MM-dd") for midnight.
     * @param to          The end date-time in the format "d/M/yyyy HHmm", or ISO date ("yyyy-MM-dd") for midnight.
     * @throws DukeException If either date-time cannot be parsed.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses a date-time string using the custom format, falling back to ISO date if necessary.
     *
     * @param dateTimeStr The date-time string.
     * @return The parsed LocalDateTime (at start of day if ISO format is used).
     * @throws DukeException If the date-time cannot be parsed.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws DukeException {
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(dateTimeStr);
                return LocalDateTime.of(date, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException e2) {
                throw new DukeException("Invalid date-time format. Please use 'd/M/yyyy HHmm' (e.g., 2/12/2019 1800) or ISO date 'yyyy-MM-dd' for midnight. Caused by: " 
                        + e2.getMessage());
            }
        }
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
     * Formats the start date-time to a more readable format (e.g., "Dec 2 2019, 6:00 PM").
     *
     * @return The formatted start date-time.
     */
    protected String formatFrom() {
        return from.format(OUTPUT_FORMAT);
    }

    /**
     * Formats the end date-time to a more readable format (e.g., "Dec 2 2019, 8:00 PM").
     *
     * @return The formatted end date-time.
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
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(INPUT_FORMAT)
                + " | " + to.format(INPUT_FORMAT);
    }
}
