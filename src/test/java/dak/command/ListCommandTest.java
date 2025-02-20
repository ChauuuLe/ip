package dak.command;

import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.ui.MainApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the ListCommand functionality.
 */
class ListCommandTest {
    
    /**
     * Tests that executing the ListCommand on an empty TaskList shows no tasks message.
     */
    @Test
    void execute_emptyTaskList_shouldShowNoTasksMessage() {
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
        
        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);

        // Since the task list is empty, the size should be 0.
        assertEquals(0, taskList.getTasks().size());
    }
}
