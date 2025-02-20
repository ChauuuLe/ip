package dak.ui;

import dak.storage.Storage;
import dak.parser.Parser;
import dak.task.TaskList;
import dak.exceptions.DukeException;
import dak.command.Command;
import java.io.IOException;

/**
 * The main chatbot logic for processing user input.
 */
public class Dak {
    private static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the Dak chatbot.
     */
    public Dak(Ui ui) {
        this.ui = ui;
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Failed to load tasks.");
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and response the message.
     * 
     * @param input The user input string.
     */
    public void response(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.printMessage("OOPS! " + e.getMessage());
        }
    }
}
