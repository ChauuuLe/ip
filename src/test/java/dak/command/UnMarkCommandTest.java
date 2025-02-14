package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test UnmarkCommand functionality.
 */
class UnmarkCommandTest {
    
    @Test
    void execute_validIndex_unmarksTask() throws DukeException {
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Read book"));
        taskList.getTasks().get(0).markAsDone(); // Mark as done initially
        
        UnmarkCommand command = new UnmarkCommand(1);
        command.execute(taskList, ui, storage);
        
        assertFalse(taskList.getTasks().get(0).isDone());
    }
    
    @Test
    void execute_invalidIndex_throwsException() {
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/data/test.txt");
        TaskList taskList = new TaskList();
        
        UnmarkCommand command = new UnmarkCommand(5);
        
        assertThrows(DukeException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
