package dak.command;

import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test ListCommand functionality.
 */
class ListCommandTest {
    
    @Test
    void execute_emptyTaskList_shouldShowNoTasksMessage() {
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);

        assertEquals(0, taskList.getTasks().size());
    }
}
