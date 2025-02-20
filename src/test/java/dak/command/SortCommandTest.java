package dak.command;

import dak.task.TaskList;
import dak.task.Todo;
import dak.task.Deadline;
import dak.task.Event;
import dak.ui.Ui;
import dak.storage.Storage;
import dak.ui.MainApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the SortCommand functionality.
 */
class SortCommandTest {

    /**
     * Tests that sorting arranges deadlines in chronological order.
     */
    @Test
    void execute_sortDeadlines_correctOrder() {
        TaskList taskList = new TaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        // Add tasks with different deadlines
        taskList.addTask(new Deadline("Submit report", "15/3/2024 1200"));
        taskList.addTask(new Deadline("Project deadline", "10/3/2024 1400"));
        taskList.addTask(new Todo("Read book"));
        taskList.addTask(new Event("Team meeting", "12/3/2024 0900", "12/3/2024 1100"));

        // Execute sorting
        SortCommand command = new SortCommand();
        command.execute(taskList, ui, storage);

        // Verify the sorting result (deadlines should be in order)
        List<String> expectedOrder = List.of(
            "Project deadline",  // 10 Mar
            "Submit report",     // 15 Mar
            "Read book",         // Todo remains
            "Team meeting"       // Event remains
        );

        List<String> actualOrder = new ArrayList<>();
        taskList.getTasks().forEach(task -> actualOrder.add(task.getDescription()));

        assertEquals(expectedOrder, actualOrder, "Tasks should be sorted by deadline.");
    }

    /**
     * Tests that sorting does not affect an already sorted list.
     */
    @Test
    void execute_sortedList_noChange() {
        TaskList taskList = new TaskList();
        Ui ui = setupUi();
        Storage storage = new Storage("./src/test/data/test.txt");

        // Add tasks in already sorted order
        taskList.addTask(new Deadline("Project deadline", "10/3/2024 1400"));
        taskList.addTask(new Deadline("Submit report", "15/3/2024 1200"));
        taskList.addTask(new Todo("Read book"));
        taskList.addTask(new Event("Team meeting", "12/3/2024 0900", "12/3/2024 1100"));

        // Execute sorting
        SortCommand command = new SortCommand();
        command.execute(taskList, ui, storage);

        // Ensure sorting does not change the order
        List<String> expectedOrder = List.of(
            "Project deadline",
            "Submit report",
            "Read book",
            "Team meeting"
        );

        List<String> actualOrder = new ArrayList<>();
        taskList.getTasks().forEach(task -> actualOrder.add(task.getDescription()));

        assertEquals(expectedOrder, actualOrder, "Sorted list should remain unchanged.");
    }

    /**
     * Helper method to create a dummy MainApp instance and return a Ui instance.
     *
     * @return A Ui instance with a dummy MainApp.
     */
    private Ui setupUi() {
        MainApp dummyApp = new MainApp() {
            @Override
            public void displayMessage(String message) {
                // No action needed for testing.
            }
        };
        return new Ui(dummyApp);
    }
}
