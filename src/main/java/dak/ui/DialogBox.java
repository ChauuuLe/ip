package dak.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom dialog box for displaying messages in the chatbot.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a dialog box with a message, an avatar image, and a flag indicating if the message is from the user.
     *
     * @param message The text message to display.
     * @param img     The image avatar for the message.
     * @param isUser  True if the message is from the user; false if from the chatbot.
     */
    public DialogBox(String message, Image img, boolean isUser) {
        setupComponents(message, img, isUser);
        styleDialogBox(isUser);
    }

    /**
     * Initializes components of the dialog box and adds them in the correct order based on the sender.
     *
     * @param message The text message to display.
     * @param img     The image avatar for the message.
     * @param isUser  True if the message is from the user; false if from the chatbot.
     */
    private void setupComponents(String message, Image img, boolean isUser) {
        text = new Label(message);
        displayPicture = new ImageView(img);
        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        
        if (isUser) {
            // For user messages, display message first then image.
            this.getChildren().addAll(text, displayPicture);
        } else {
            // For chatbot messages, display image first then message.
            this.getChildren().addAll(displayPicture, text);
        }
    }

    /**
     * Styles the dialog box by setting its alignment based on the sender.
     *
     * @param isUser True if the message is from the user; false if from the chatbot.
     */
    private void styleDialogBox(boolean isUser) {
        setAlignment(isUser ? Pos.TOP_RIGHT : Pos.TOP_LEFT);
    }
}
