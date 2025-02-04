package ui;

import storage.Storage;
import parser.Parser;
import task.TaskList;
import exceptions.DukeException;
import command.Command;
import java.io.IOException;

/**
 * The main chatbot class that initializes and runs the chatbot.
 */
public class Dak {
    private static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the Dak chatbot.
     */
    public Dak() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot's main loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.showGoodbye();
    }


    /**
     * The entry point of the program.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Dak().run();
    }
}
