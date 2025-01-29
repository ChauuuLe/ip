import java.util.Scanner;
public class Dak {
    public static void printMessage(String message) {
        System.out.println("  ____________________________________________________________");
        System.out.println("  " + message);
        System.out.println("  ____________________________________________________________");
    }

    public static void greeting() {
        printMessage("Hello, I'm Dak\nWhat can I do for you?");
    }
    
    public static void bye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {  
        greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            if (message.equals("bye")) {
                break;
            }
            printMessage(message);    
        }
        bye();
    }
}
