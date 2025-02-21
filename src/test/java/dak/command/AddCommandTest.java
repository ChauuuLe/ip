package dak.command;

import dak.task.TaskList;
import dak.task.Task;
import dak.storage.Storage;
import dak.ui.Ui;
import dak.ui.MainApp;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the AddCommand functionality.
 */
class AddCommandTest {

    /**
     * Tests that executing the "todo" command successfully adds a task to the TaskList.
     *
     * @throws DukeException if an error occurs during task addition.
     */
    @Test
    void execute_todoTask_addsSuccessfully() throws DukeException {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("todo Read book");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size());
        assertEquals("Read book", taskList.getTasks().get(0).getDescription());
    }

    /**
     * Tests that executing the "deadline" command successfully adds a deadline task.
     *
     * @throws DukeException if an error occurs during task addition.
     */
    @Test
    void execute_deadlineTask_addsSuccessfully() throws DukeException {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("deadline Finish project /by 12/3/2024 1400");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size());
        Task task = taskList.getTasks().get(0);
        assertEquals("Finish project", task.getDescription());
    }

    /**
     * Tests that executing the "event" command successfully adds an event task.
     *
     * @throws DukeException if an error occurs during task addition.
     */
    @Test
    void execute_eventTask_addsSuccessfully() throws DukeException {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("event Team meeting /from 14/3/2024 0900 /to 14/3/2024 1100");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size());
        Task task = taskList.getTasks().get(0);
        assertEquals("Team meeting", task.getDescription());
    }

    /**
     * Tests that executing an invalid "todo" command (empty description) throws a DukeException.
     */
    @Test
    void execute_emptyTodo_throwsException() {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("todo ");
        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
    }

    /**
     * Tests that executing an invalid "deadline" command (missing deadline) throws a DukeException.
     */
    @Test
    void execute_invalidDeadline_throwsException() {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("deadline Finish project");
        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
    }

    /**
     * Tests that executing an invalid "event" command (missing time period) throws a DukeException.
     */
    @Test
    void execute_invalidEvent_throwsException() {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("event Conference /from 15/3/2024 1000");
        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
    }

    /**
     * Tests that executing an unknown command throws a DukeException.
     */
    @Test
    void execute_unknownCommand_throwsException() {
        TaskList taskList = setupTaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        AddCommand command = new AddCommand("reminder Buy milk");
        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
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

    /**
     * Helper method to initialize an empty TaskList.
     *
     * @return A new empty TaskList.
     */
    private TaskList setupTaskList() {
        return new TaskList();
    }
}
