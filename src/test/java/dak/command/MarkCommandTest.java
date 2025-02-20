package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.ui.MainApp;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the MarkCommand functionality.
 */
class MarkCommandTest {
    
    /**
     * Tests that executing the MarkCommand with a valid index marks the corresponding task as done.
     *
     * @throws DukeException if an error occurs during marking.
     */
    @Test
    void execute_validIndex_marksTaskAsDone() throws DukeException {
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
        taskList.addTask(new Todo("Read book"));
        
        MarkCommand command = new MarkCommand(1);
        command.execute(taskList, ui, storage);
        
        assertTrue(taskList.getTasks().get(0).isDone());
    }
    
    /**
     * Tests that executing the MarkCommand with an invalid index throws a DukeException.
     */
    @Test
    void execute_invalidIndex_throwsException() {
        // Create a dummy MainApp to satisfy Ui's constructor requirement.
        MainApp dummyApp = new MainApp() {
            @Override
            public void displayMessage(String message) {
                // No action needed for testing.
            }
        };
        Ui ui = new Ui(dummyApp);
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList();
        
        MarkCommand command = new MarkCommand(5);
        
        assertThrows(DukeException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
