package dak.exceptions;

/**
 * Handle exception error specify for chatbot
 */
public class DukeException extends Exception {
    /**
     * Create Exception with error message
     * 
     * @param message The error message
     */
    public DukeException(String message) {
        super(message);
    }
}
