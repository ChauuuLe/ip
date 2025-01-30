import java.util.Scanner;
import java.util.ArrayList;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import exceptions.DukeException;

public class Dak {
    public static void printMessage(String message) {
        System.out.println("  ____________________________________________________________");
        System.out.println("  " + message);
        System.out.println("  ____________________________________________________________");
    }

    public static void greeting() {
        printMessage("Hello, I'm Dak\n  What can I do for you?");
    }

    public static void bye() {
        printMessage("Bye. Hope to see you again soon!");
    }

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
            printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + listItem.size() + " tasks in the list.");

        } else if (message.startsWith("deadline ")) {
            try {
                String[] parts = message.substring(9).split(" /by ");
                if (parts.length < 2) {
                    throw new DukeException("Invalid format. Use: deadline <description> /by <time>");
                }
                String description = parts[0].trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new DukeException("Both the description and deadline time must be provided.");
                }
                Task task = new Deadline(description, by);
                listItem.add(task);
                printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + listItem.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Invalid format. Use: deadline <description> /by <time>");
            }

        } else if (message.startsWith("event ")) {
            try {
                String[] parts = message.substring(6).split(" /from ");
                if (parts.length < 2) {
                    throw new DukeException("Invalid format. Use: event <description> /from <start time> /to <end time>");
                }
                String description = parts[0].trim();
                String[] timeParts = parts[1].split(" /to ");
                if (timeParts.length < 2) {
                    throw new DukeException("Invalid format. Use: event <description> /from <start time> /to <end time>");
                }
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new DukeException("All event details (description, start time, and end time) must be provided.");
                }
                Task task = new Event(description, from, to);
                listItem.add(task);
                printMessage("Got it. I've added this task:\n  " + task + "\n  Now you have " + listItem.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Invalid format. Use: event <description> /from <start time> /to <end time>");
            }

        } else {
            throw new DukeException("I'm sorry, but I don't know what that means. Please try again with a valid command.");
        }
    }

    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listItem = new ArrayList<>();

        while (true) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                break;
            }

            try {
                handleCommand(message, listItem);
            } catch (DukeException e) {
                printMessage("OOPS!!! " + e.getMessage());
            }
        }

        bye();
    }
}
