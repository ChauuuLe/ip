package dak.command;

import dak.task.TaskList;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test AddCommand functionality.
 */
class AddCommandTest {

    @Test
    void execute_todoTask_addsSuccessfully() throws DukeException {
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        AddCommand command = new AddCommand("todo Read book");
        command.execute(taskList, ui, storage);
        
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Read book", taskList.getTasks().get(0).getDescription());
    }
}
