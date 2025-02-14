package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test DeleteCommand functionality.
 */
class DeleteCommandTest {

    @Test
    void execute_validIndex_deletesTask() throws DukeException {
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Read book"));
        
        DeleteCommand command = new DeleteCommand(1);
        command.execute(taskList, ui, storage);
        
        assertEquals(0, taskList.getTasks().size());
    }
    
    @Test
    void execute_invalidIndex_throwsException() {
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        DeleteCommand command = new DeleteCommand(5);
        
        assertThrows(DukeException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
