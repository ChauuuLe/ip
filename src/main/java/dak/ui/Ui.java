package dak.ui;

/**
 * The UI class for Dak.
 */
public class Ui {
    private MainApp mainApp;

    /**
     * Constructs a Ui instance with reference to the main JavaFX UI.
     *
     * @param mainApp The main application.
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
        mainApp.displayMessage(message, false);
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
