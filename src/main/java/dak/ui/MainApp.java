package dak.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The JavaFX application for the Dak chatbot.
 */
public class MainApp extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Ui ui;
    private Dak dak;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

    @Override
    public void start(Stage stage) {
        setupUI(stage);
        setupChatbot();
        setupEventHandlers();
    }

    /**
     * Sets up the main user interface components and layout.
     *
     * @param stage The primary stage for this application.
     */
    private void setupUI(Stage stage) {
        initializeComponents();
        configureScrollPane();
        configureInputField();
        configureSendButton();
        setupLayoutConstraints();

        AnchorPane mainLayout = new AnchorPane(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout, 400, 600);
        
        stage.setScene(scene);
        stage.setTitle("Dak - Your Chatbot");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Initializes UI components.
     */
    private void initializeComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
        scrollPane.setContent(dialogContainer);
    }

    /**
     * Configures the scroll pane to properly display chat messages.
     */
    private void configureScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Configures the text input field.
     */
    private void configureInputField() {
        userInput.setPrefWidth(325.0);
    }

    /**
     * Configures the send button.
     */
    private void configureSendButton() {
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Sets up layout constraints for UI elements.
     */
    private void setupLayoutConstraints() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
    }

    /**
     * Initializes the chatbot and displays a welcome message.
     */
    private void setupChatbot() {
        ui = new Ui(this);
        dak = new Dak(ui);
        displayMessage("Dak: Hello! How can I assist you today?", false);
    }

    /**
     * Sets up event handlers for user input.
     */
    private void setupEventHandlers() {
        sendButton.setOnAction(e -> handleUserInput());
        userInput.setOnAction(e -> handleUserInput());
    }

    /**
     * Handles user input and chatbot response.
     */
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            displayMessage("You: " + input, true);
            dak.response(input);
            if (input.equalsIgnoreCase("bye")) {
                // Close the stage (application window) if the user says "bye"
                ((Stage) userInput.getScene().getWindow()).close();
            }
            userInput.clear();
        }
    }


    /**
     * Displays a message in the chat history.
     *
     * @param message The message to display.
     * @param isUser  True if message is from the user, false if from the chatbot.
     */
    public void displayMessage(String message, boolean isUser) {
        dialogContainer.getChildren().add(new DialogBox(message, isUser ? userImage : botImage, isUser));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
