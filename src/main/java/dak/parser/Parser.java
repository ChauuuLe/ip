package dak.parser;

import dak.command.Command;
import dak.command.ExitCommand;
import dak.command.ListCommand;
import dak.command.MarkCommand;
import dak.command.DeleteCommand;
import dak.command.AddCommand;
import dak.command.UnmarkCommand;
import dak.command.FindCommand;
import dak.command.SortCommand;
import dak.exceptions.DukeException;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Parses a user input string.
     *
     * @param input The user input string.
     * @return The corresponding Command object.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String input) throws DukeException {
        input = input.trim();
        
        if (isSimpleCommand(input)) {
            return parseSimpleCommand(input);
        } else if (isTaskNumberCommand(input)) {
            return parseTaskNumberCommand(input);
        } else if (isAddCommand(input)) {
            return parseAddCommand(input);
        } else if (input.startsWith("find ")) {
            return parseFindCommand(input);
        }

        throw new DukeException("I'm sorry, but I don't know what that means.");
    }

    /**
     * Determines if the input is a simple command (e.g., list, bye).
     */
    private static boolean isSimpleCommand(String input) {
        return input.equals("bye") || input.equals("list") || input.equals("sort");
    }

    /**
     * Parses simple commands like "bye" and "list".
     */
    private static Command parseSimpleCommand(String input) {
        switch (input) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "sort":
                return new SortCommand();
            default:
                throw new IllegalStateException("Unexpected command in parseSimpleCommand");
        }
    }

    /**
     * Determines if the input is a command that requires a task number (e.g., mark, unmark, delete).
     */
    private static boolean isTaskNumberCommand(String input) {
        return input.startsWith("mark ") || input.startsWith("unmark ") || input.startsWith("delete ");
    }

    /**
     * Parses commands that require a task number (mark, unmark, delete).
     */
    private static Command parseTaskNumberCommand(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide a valid task number.");
        }

        try {
            int taskIndex = Integer.parseInt(parts[1].trim());
            switch (parts[0]) {
                case "mark":
                    return new MarkCommand(taskIndex);
                case "unmark":
                    return new UnmarkCommand(taskIndex);
                case "delete":
                    return new DeleteCommand(taskIndex);
                default:
                    throw new IllegalStateException("Unexpected command in parseTaskNumberCommand");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number.");
        }
    }

    /**
     * Determines if the input is an add command (todo, deadline, event).
     */
    private static boolean isAddCommand(String input) {
        return input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ");
    }

    /**
     * Parses "todo", "deadline", and "event" commands.
     */
    private static Command parseAddCommand(String input) {
        return new AddCommand(input);
    }

    /**
     * Parses the "find" command.
     */
    private static Command parseFindCommand(String input) throws DukeException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new DukeException("Please provide a non-empty keyword for the find command.");
        }
        return new FindCommand(keyword);
    }
}
