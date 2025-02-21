package dak.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main JavaFX application class for Dak.
 */
public class MainApp extends Application {
    private Ui ui;
    private Dak dak;
    private MainWindow mainWindow; // Controller loaded from FXML

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            mainWindow = fxmlLoader.getController();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Dak - Your Chatbot");
            stage.setResizable(false);
            stage.show();
            
            // Set up the UI and Dak following the old logic.
            ui = new Ui(this);
            dak = new Dak(ui);
            
            // Pass the Dak instance to the MainWindow controller (if needed)
            mainWindow.setDak(dak);
            
            // Show a welcome message
            ui.showWelcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays a message on the UI. This method is called by Ui.
     *
     * @param message The message to display.
     * @param isUser  True if the message is from the user; false if from Dak.
     */
    public void displayMessage(String message, boolean isUser) {
        if (mainWindow != null) {
            mainWindow.displayMessage(message, isUser);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
