package parser;

import command.Command;
import command.ExitCommand;
import command.AddCommand;
import command.DeleteCommand;
import exceptions.DukeException;

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
        } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
            return new AddCommand(input);
        } else if (input.startsWith("delete ")) {
            try {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number for deletion.");
            }
        }

        throw new DukeException("I'm sorry, but I don't know what that means.");
    }
}
