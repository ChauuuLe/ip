import java.util.Scanner;
import java.util.ArrayList;
import task.Task;

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

    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listItem = new ArrayList<>();

        while (true) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                break;

            } else if (message.equals("list")) {
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
                        printMessage("Invalid task number.");
                    } else {
                        Task task = listItem.get(taskNumber - 1);
                        task.markAsDone();
                        printMessage("Nice! I've marked this task as done:\n  " + task);
                    }
                } catch (NumberFormatException e) {
                    printMessage("Please provide a valid task number.");
                }

            } else if (message.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(message.split(" ")[1]);
                    if (taskNumber <= 0 || taskNumber > listItem.size()) {
                        printMessage("Invalid task number.");
                    } else {
                        Task task = listItem.get(taskNumber - 1);
                        task.markAsNotDone();
                        printMessage("OK, I've marked this task as not done yet:\n  " + task);
                    }
                } catch (NumberFormatException e) {
                    printMessage("Please provide a valid task number.");
                }

            } else {
                listItem.add(new Task(message));
                printMessage("added: " + message);
            }
        }

        bye();
    }
}
