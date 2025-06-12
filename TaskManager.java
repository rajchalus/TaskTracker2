import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String name;
    int importance; // 1 (low) to 5 (high)

    public Task(String name, int importance) {
        this.name = name;
        this.importance = importance;
    }

    @Override
    public String toString() {
        return name + " (Importance: " + importance + ")";
    }
}

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    listTasks();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter importance (1–5): ");
        int importance = Integer.parseInt(scanner.nextLine());
        tasks.add(new Task(name, importance));
        System.out.println("Task added!");
    }

    private static void listTasks() {
        System.out.println("\nYour Tasks:");
        for (Task task : tasks) {
            System.out.println("- " + task);
        }
    }
}
