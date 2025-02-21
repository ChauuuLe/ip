package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.ui.MainApp;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the DeleteCommand functionality.
 */
class DeleteCommandTest {

    /**
     * Tests that executing the DeleteCommand with a valid index deletes the corresponding task.
     *
     * @throws DukeException if an error occurs during deletion.
     */
    @Test
    void execute_validIndex_deletesTask() throws DukeException {
        Ui ui = setupUi();
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Read book"));
        
        DeleteCommand command = new DeleteCommand(1);
        command.execute(taskList, ui, storage);
        
        assertEquals(0, taskList.getTasks().size());
    }
    
    /**
     * Tests that executing the DeleteCommand with an invalid index throws a DukeException.
     */
    @Test
    void execute_invalidIndex_throwsException() {
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        DeleteCommand command = new DeleteCommand(5);
        
        assertThrows(DukeException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }

    /**
     * Helper method to create a dummy MainApp instance and return a Ui instance.
     *
     * @return A Ui instance with a dummy MainApp.
     */
    private Ui setupUi() {
        MainApp dummyApp = new MainApp() {
            public void displayMessage(String message) {
                // No action needed for testing.
            }
        };
        return new Ui(dummyApp);
    }
}
