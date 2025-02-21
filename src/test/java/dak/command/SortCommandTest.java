package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.task.Deadline;
import dak.task.Event;
import dak.ui.Ui;
import dak.storage.Storage;
import dak.ui.MainApp;
import dak.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the SortCommand functionality.
 */
class SortCommandTest {

    /**
     * DummyUi extends Ui and captures printed messages for verification.
     */
    private static class DummyUi extends Ui {
        private String printedMessage;

        public DummyUi(MainApp dummyApp) {
            super(dummyApp);
        }

        @Override
        public void printMessage(String message) {
            this.printedMessage = message;
        }

        public String getPrintedMessage() {
            return printedMessage;
        }
    }

    /**
     * Tests that sorting displays deadlines in chronological order.
     */
    @Test
    void execute_sortDeadlines_correctOrder() {
        TaskList taskList = new TaskList();
        DummyUi ui = setupDummyUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        addTestTasks(taskList);

        SortCommand command = new SortCommand();
        command.execute(taskList, ui, storage);

        String expectedMessage = "Here are the deadlines in your list:" +
                "\n  1. [D][ ] Project deadline (by: Mar 10 2024, 2:00 PM)" +
                "\n  2. [D][ ] Submit report (by: Mar 15 2024, 12:00 PM)";

        assertEquals(expectedMessage, ui.getPrintedMessage(),
                "The printed deadline list should match the expected order.");
    }

    /**
     * Tests that sorting prints a message indicating no deadlines when none exist.
     */
    @Test
    void execute_noDeadlines_printsNoDeadlineMessage() {
        TaskList taskList = new TaskList();
        DummyUi ui = setupDummyUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        try {
            taskList.addTask(new Todo("Read book"));
            taskList.addTask(new Event("Team meeting", "12/3/2024 0900", "12/3/2024 1100"));
        } catch (DukeException e) {
            fail("Unexpected DukeException while creating tasks: " + e.getMessage());
        }

        SortCommand command = new SortCommand();
        command.execute(taskList, ui, storage);

        String expectedMessage = "There are no deadline tasks in your list.";
        assertEquals(expectedMessage, ui.getPrintedMessage(),
                "The printed message should indicate no deadlines.");
    }

    /**
     * Helper method to add test tasks to the task list with proper exception handling.
     *
     * @param taskList The task list to which test tasks will be added.
     */
    private void addTestTasks(TaskList taskList) {
        try {
            taskList.addTask(new Deadline("Submit report", "15/3/2024 1200"));
            taskList.addTask(new Deadline("Project deadline", "10/3/2024 1400"));
            taskList.addTask(new Todo("Read book"));
            taskList.addTask(new Event("Team meeting", "12/3/2024 0900", "12/3/2024 1100"));
        } catch (DukeException e) {
            fail("Unexpected DukeException while creating tasks: " + e.getMessage());
        }
    }

    /**
     * Helper method to create a dummy MainApp instance and return a DummyUi instance.
     *
     * @return A DummyUi instance with a dummy MainApp.
     */
    private DummyUi setupDummyUi() {
        MainApp dummyApp = new MainApp() {
            public void displayMessage(String message) {
                // No action needed.
            }
        };
        return new DummyUi(dummyApp);
    }
}
