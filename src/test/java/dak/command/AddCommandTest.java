package dak.command;

import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.ui.MainApp;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the AddCommand functionality.
 */
class AddCommandTest {

    /**
     * Tests that executing the "todo" command successfully adds a task to the TaskList.
     * <p>
     * This test creates a dummy MainApp instance for the Ui, sets up the storage and task list,
     * executes the command, and then verifies that the task was added correctly.
     * </p>
     *
     * @throws DukeException if an error occurs during task addition.
     */
    @Test
    void execute_todoTask_addsSuccessfully() throws DukeException {
        // Create a dummy MainApp to satisfy Ui's constructor requirement.
        MainApp dummyApp = new MainApp() {
            @Override
            public void displayMessage(String message) {
                // No action needed for testing.
            }
        };

        Ui ui = new Ui(dummyApp);
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        AddCommand command = new AddCommand("todo Read book");
        command.execute(taskList, ui, storage);
        
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Read book", taskList.getTasks().get(0).getDescription());
    }
}
