import java.util.Scanner;
import java.util.ArrayList;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import exceptions.DukeException;
import storage.Storage;
import java.io.IOException;

/**
 * The main chatbot class that manages user interactions and tasks.
 */
public class Dak {
    private static final String FILE_PATH = "./data/duke.txt";
    private static Storage storage;
    private static ArrayList<Task> tasks;

    /**
     * Prints a message within a formatted box.
     *
     * @param message The message to print.
     */
    public static void printMessage(String message) {
        System.out.println("  ____________________________________________________________");
        System.out.println("  " + message);
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Displays the chatbot's greeting message.
     */
    public static void greeting() {
        printMessage("Hello, I'm Dak\n  What can I do for you?");
    }

    /**
     * Displays the chatbot's farewell message.
     */
    public static void bye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Handles user commands and modifies the task list accordingly.
     *
     * @param message The user's command input.
     * @param listItem The list of tasks.
     * @throws DukeException If the command is invalid.
     */
    public static void handleCommand(String message, ArrayList<Task> listItem) throws DukeException {
        if (message.equals("list")) {
            if (listItem.isEmpty()) {
                printMessage("You have no tasks in your list.");
            } else {
                StringBuilder listItemMessage = new StringBuilder("Here are the tasks in your list:");
                for (int id = 1; id <= listItem.size(); id++) {
                    listItemMessage.append("\n  ").append(id).append(". ").append(listItem.get(id - 1));
                }
                printMessage(listItemMessage.toString());
            }

        } else if (message.startsWith("mark ")) {
            try {
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                if (taskNumber <= 0 || taskNumber > listItem.size()) {
                    throw new DukeException("Invalid task number. Please provide a valid task number.");
                }
                Task task = listItem.get(taskNumber - 1);
                task.markAsDone();
                saveTasks();
                printMessage("Nice! I've marked this task as done:\n  " + task);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number for the mark command.");
            }

        } else if (message.startsWith("unmark ")) {
            try {
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                if (taskNumber <= 0 || taskNumber > listItem.size()) {
                    throw new DukeException("Invalid task number. Please provide a valid task number.");
                }
                Task task = listItem.get(taskNumber - 1);
                task.markAsNotDone();
                saveTasks();
                printMessage("OK, I've marked this task as not done yet:\n  " + task);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number for the unmark command.");
            }

        } else if (message.startsWith("todo ")) {
            String description = message.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task task = new Todo(description);
            listItem.add(task);
            saveTasks();
            printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + listItem.size() + " tasks in the list.");

        } else if (message.startsWith("deadline ")) {
            try {
                // Expect input like: deadline return book /by 2/12/2019 1800
                String[] parts = message.substring(9).split(" /by ");
                if (parts.length < 2) {
                    throw new DukeException("Invalid format. Use: deadline <description> /by <d/M/yyyy HHmm>");
                }
                String description = parts[0].trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new DukeException("Both the description and deadline date-time must be provided.");
                }

                // Create a new Deadline task with the custom date-time format
                Task task = new Deadline(description, by);
                listItem.add(task);
                saveTasks();
                printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + listItem.size() + " tasks in the list.");
            } catch (Exception e) {
                throw new DukeException("Invalid date-time format. Use d/M/yyyy HHmm (e.g., 2/12/2019 1800).");
            }
        } else if (message.startsWith("event ")) {
            try {
                // Expect input like: event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600
                String[] parts = message.substring(6).split(" /from ");
                if (parts.length < 2) {
                    throw new DukeException("Invalid format. Use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
                }
                String description = parts[0].trim();
                String[] timeParts = parts[1].split(" /to ");
                if (timeParts.length < 2) {
                    throw new DukeException("Invalid format. Use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
                }
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new DukeException("All event details (description, start time, and end time) must be provided.");
                }

                // Create a new Event task with the custom date-time format
                Task task = new Event(description, from, to);
                listItem.add(task);
                saveTasks();
                printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + listItem.size() + " tasks in the list.");
            } catch (Exception e) {
                throw new DukeException("Invalid date-time format. Use d/M/yyyy HHmm (e.g., 2/12/2019 1400).");
            }
        } else if (message.startsWith("delete ")) {
            try {
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                if (taskNumber <= 0 || taskNumber > listItem.size()) {
                    throw new DukeException("Invalid task number. Please provide a valid task number to delete.");
                }
                Task removedTask = listItem.remove(taskNumber - 1);
                saveTasks();
                printMessage("Noted. I've removed this task:\n  " + removedTask + "\n  Now you have " + listItem.size() + " tasks in the list.");
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number to delete.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the task number to delete.");
            }

        } else {
            throw new DukeException("I'm sorry, but I don't know what that means. Please try again with a valid command.");
        }
    }

    /**
     * Saves the current list of tasks to the storage file.
     */
    private static void saveTasks() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            printMessage("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * The main method that initializes the chatbot and starts the user interaction loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        storage = new Storage(FILE_PATH);

        try {
            tasks = storage.load();
        } catch (IOException e) {
            printMessage("Failed to load tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        greeting();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                break;
            }

            try {
                handleCommand(message, tasks);
            } catch (DukeException e) {
                printMessage("OOPS!!! " + e.getMessage());
            }
        }

        bye();
    }
}
