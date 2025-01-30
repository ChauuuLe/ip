import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> listItem = new ArrayList<>();
        while (true) {
            String message = sc.nextLine();
            if (message.equals("bye")) {
                break;
            } else if (message.equals("list")) {
                String listItemMessage = "";
                for (int id = 1; id <= listItem.size(); id++) {
                    listItemMessage += id + ". " + listItem.get(id - 1);
                    if (id + 1 <= listItem.size())
                        listItemMessage += "\n  ";
                }
                printMessage(listItemMessage);
            } else {
                listItem.add(message);
                printMessage("added: " + message);
            }   
        }
        bye();
    }
}
