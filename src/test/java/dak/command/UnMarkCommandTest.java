package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.ui.MainApp;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the UnmarkCommand functionality.
 */
class UnmarkCommandTest {
    
    /**
     * Tests that executing the UnmarkCommand on a task marked as done successfully unmarks it.
     *
     * @throws DukeException if an error occurs during unmarking.
     */
    @Test
    void execute_validIndex_unmarksTask() throws DukeException {
        // Create a dummy MainApp instance to satisfy Ui's constructor requirement.
        MainApp dummyApp = new MainApp() {
            @Override
            public void displayMessage(String message) {
                // No action needed for testing.
            }
        };
        Ui ui = new Ui(dummyApp);
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Read book"));
        // Mark the task as done initially.
        taskList.getTasks().get(0).markAsDone();
        
        UnmarkCommand command = new UnmarkCommand(1);
        command.execute(taskList, ui, storage);
        
        assertFalse(taskList.getTasks().get(0).isDone());
    }
    
    /**
     * Tests that executing the UnmarkCommand with an invalid index throws a DukeException.
     */
    @Test
    void execute_invalidIndex_throwsException() {
        // Create a dummy MainApp instance to satisfy Ui's constructor requirement.
        MainApp dummyApp = new MainApp() {
            @Override
            public void displayMessage(String message) {
                // No action needed for testing.
            }
        };
        Ui ui = new Ui(dummyApp);
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        UnmarkCommand command = new UnmarkCommand(5);
        
        assertThrows(DukeException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
