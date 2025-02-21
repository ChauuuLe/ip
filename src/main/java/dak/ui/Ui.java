package dak.ui;

/**
 * Handles user interaction via JavaFX.
 */
public class Ui {

    private MainApp mainApp; // Reference to JavaFX UI

    /**
     * Constructs a Ui object.
     */
    public Ui(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sends a message to be displayed in the JavaFX UI.
     *
     * @param message The message to display.
     */
    public void printMessage(String message) {
        mainApp.displayMessage("Dak: " + message, false);
    }

    /**
     * Shows an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        mainApp.displayMessage("OOPS!!! " + message, false);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        mainApp.displayMessage("Hello, I'm Dak 🤖\nWhat can I do for you?", false);
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        mainApp.displayMessage("Bye. Hope to see you again soon!", false);
    }
}
