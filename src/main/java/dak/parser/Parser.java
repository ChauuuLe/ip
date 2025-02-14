package dak.parser;

import dak.command.*;
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
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            try {
                int taskIndex = Integer.parseInt(input.substring(5).trim());
                return new MarkCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number for the mark command.");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                return new UnmarkCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number for the unmark command.");
            }
        } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
            return new AddCommand(input);
        } else if (input.startsWith("delete ")) {
            try {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number for the delete command.");
            }
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new DukeException("Please provide a non-empty keyword for the find command.");
            } else {
                return new FindCommand(keyword);
            }
        }

        throw new DukeException("I'm sorry, but I don't know what that means.");
    }
}
