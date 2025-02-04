package dak.ui;

import java.util.Scanner;

/**
 * Handles user interactions.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a message with a formatted box.
     *
     * @param message The message to print.
     */
    public void printMessage(String message) {
        System.out.println("  ____________________________________________________________");
        System.out.println("  " + message);
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        printMessage("Hello, I'm Dak\n  What can I do for you?");
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Shows an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        printMessage("OOPS!!! " + message);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        printMessage("Failed to load tasks from storage.");
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println("  ____________________________________________________________");
    }
}
