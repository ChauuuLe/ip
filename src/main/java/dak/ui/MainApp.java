package dak.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The JavaFX application for the Dak chatbot.
 */
public class MainApp extends Application {
    private Ui ui;
    private Dak dak;
    private TextArea chatHistory;

    @Override
    public void start(Stage stage) {
        chatHistory = new TextArea();
        chatHistory.setEditable(false);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        sendButton.setOnAction(e -> {
            String input = userInput.getText();
            if (!input.isEmpty()) {
                displayMessage("You: " + input);
                dak.response(input);
                userInput.clear();

                if (input.equalsIgnoreCase("bye")) {
                    stage.close();
                }
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(chatHistory, userInput, sendButton);

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("Dak - Your Chatbot");
        stage.setScene(scene);
        stage.show();

        // Initialize chatbot logic
        ui = new Ui(this);
        dak = new Dak(ui);
        ui.showWelcome();
    }

    /**
     * Displays a message in the chat history.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        chatHistory.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
