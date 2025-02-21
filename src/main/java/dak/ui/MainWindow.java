package dak.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dak dak;

    // Load the images for the dialog boxes.
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dakImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

    @FXML
    public void initialize() {
        // Bind the vertical scroll value to the dialog container's height.
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays a message in the dialog container.
     *
     * @param message The message to display.
     * @param isUser  True if the message is from the user; false if from Dak.
     */
    public void displayMessage(String message, boolean isUser) {
        if (isUser) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDakDialog(message, dakImage));
        }
    }

    /**
     * Handles user input: displays the user's message, calls dak.response,
     * and if input is "bye", closes the window.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            displayMessage(input, true);
            dak.response(input);
            if (input.equalsIgnoreCase("bye")) {
                ((Stage) userInput.getScene().getWindow()).close();
            }
            userInput.clear();
        }
    }

    /**
     * Sets the Dak instance.
     *
     * @param dak The Dak instance.
     */
    public void setDak(Dak dak) {
        this.dak = dak;
    }
}
